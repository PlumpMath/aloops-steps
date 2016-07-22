(defproject hello-quil "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "2.4.0"]
                 ;[quil/quil "2.0.1-juan"]
                 ]
  :main hello-quil.prob2
  :aot [hello-quil.prob2]
  )

;; If you specify a namespace without a function, as we do above,
;; then Leiningen defaults to using the -main function.
