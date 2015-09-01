(ns leiningen.new.quintype-app
  (:require [clojure.string :as str]
            [leiningen.core.main :as main]
            [leiningen.new.templates :refer [renderer name-to-path ->files project-name
                                             multi-segment sanitize-ns]]))

(def render (renderer "quintype-app"))

(defn quintype-app
  "FIXME: write documentation"
  [name]
  (let [main-ns (str/replace (multi-segment (sanitize-ns name)) #".core$" "")
        data {:raw-name name
              :name (project-name name)
              :sanitized (name-to-path name)
              :namespace main-ns
              :nested-dirs (name-to-path main-ns)}]
    (main/info "Generating a new Quintype App")
    (->files data
             ;; Standard Things
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["doc/intro.md" (render "intro.md" data)]
             [".gitignore" (render "gitignore" data)]

             ;; Quintype things
             ["etc/dev/config.edn" (render "config.edn" data)]
             ["src/{{nested-dirs}}/main.java" (render "main.java" data)]
             ["src/{{nested-dirs}}/core.clj" (render "core.clj" data)]
             ["test/{{nested-dirs}}/core_test.clj" (render "core_test.clj" data)]
             ["resources/log4j2.xml" (render "log4j2.xml" data)]

             ;; Things to help with CI and deploy
             ["run" (render "run" data) :executable true]
             ["bin/build-tarball" (render "build-tarball" data) :executable true])))
