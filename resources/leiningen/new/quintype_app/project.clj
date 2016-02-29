(defproject {{raw-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://github.com/quintype"
  :local-repo ".m2"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [ring "1.4.0"]
                 [org.eclipse.jetty/jetty-jmx "9.2.10.v20150310"]
                 [cider/cider-nrepl "0.10.2"]
                 [clj-log4j2 "0.2.0"]]
  :main ^:skip-aot {{namespace}}.core
  :java-source-paths ["src"]
  :profiles {:uberjar {:aot []
                       :main ^:skip-aot {{namespace}}.main}
             :test {:jvm-opts ["-Dlog4j.configurationFile=file:test/quiet.xml"]}})
