(ns {{namespace}}.core
    (:require [cider.nrepl :as cider-nrepl]
              [clj-log4j2.core :as log]
              [clojure.tools.nrepl.server :as nrepl]
              [ring.adapter.jetty :as ring]
              [ring.middleware.content-type :as content-type]
              [ring.middleware.keyword-params :as keyword-params]
              [ring.middleware.params :as params])
  (:import (java.lang Thread)))

(defonce config (atom {}))

(defn- start-nrepl [{:keys [port]}]
  (nrepl/start-server
    :port port
    :handler cider-nrepl/cider-nrepl-handler))

(defn- wrap-log-timing [handler]
  (fn [request]
    (let [start-time (System/currentTimeMillis)
          result (try
                   (handler request)
                   (catch Exception e
                     (log/error e {:uri (:uri request)
                                   :query-string (:query-string request)
                                   :request-method (:request-method request)
                                   :time-elapsed (- (System/currentTimeMillis) start-time)})))]
      (log/info {:uri (:uri request)
                 :query-string (:query-string request)
                 :request-method (:request-method request)
                 :time-elapsed (- (System/currentTimeMillis) start-time)
                 :status (:status result)})
      result)))

(defn- make-handler []
  (-> (fn [request]
        {:status 200
         :body "pong"})
      content-type/wrap-content-type
      keyword-params/wrap-keyword-params
      params/wrap-params
      wrap-log-timing))

(defn- parse-config [--config]
  (-> --config
      (or "./etc/dev/config.edn")
      slurp
      read-string))

(defn set-config [--config]
  (->> --config
       parse-config
       (reset! config)))


(defn server-start [{:keys [server] :as this}]
  (ring/run-jetty (make-handler)
                  {:port  (:port server)
                   :join? false}))


(defn -main [task & {:strs [--config]}]
    (set-config --config)
    (start-nrepl (:nrepl @config))
    (server-start @config)
    (log/info "Server up and running!"))
