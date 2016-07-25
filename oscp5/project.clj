(defproject oscp5 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "2.4.0-osc"]
                 ;[quil/quil "2.4.0-osc"]
                 ;[quil/quil "2.0.1-juan"]
                 [de.sojamo/oscp5 "0.9.8"] ;; Accedemos a oscp5 por aquí, en vez de a través
                                           ;; de una librería local situada en /lib
                 ]
  ;:resource-paths ["lib/oscP5.jar"]
  :main oscp5.main)
