(defproject {{raw-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/quintype"
  :local-repo ".m2"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.nrepl "0.2.6"]
                 [ring "1.4.0"]
                 [cider/cider-nrepl "0.9.1"]
                 [clj-log4j2 "0.1.0"]]
  :main ^:skip-aot {{namespace}}.main
  :java-source-paths ["src"]
  :profiles {:uberjar {:aot []}})
