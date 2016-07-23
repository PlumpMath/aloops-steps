(defproject quil-uberjar "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "2.4.0"]]
  :main quil-uberjar.core
  :aot [quil-uberjar.core])

;; If you specify a namespace without a function, as we do above,
;; then Leiningen defaults to using the -main function.

;; Runnable jar

;; Add (:gen-class) to sketch's namespace declaration.

;; Add -main function to sketch's namespace that launches sketch.

;; You should use sketch function instead of defsketch to run sketch.
;; The latter is good for interactive development and doesn't give any benefits when using in runnable jar.

;; Add :aot and :main settings to project.clj. They should point to namespace with -main function. Read more about this options in sample.project.clj.

;; Run lein uberjar. It will create jar file with all dependencies included. Example of generated jar: target/runnable-quil-0.1.0-SNAPSHOT-standalone.jar

;; Run java -jar $YOUR_JAR or double click on jar file and enjoy your wonderful sketch!
