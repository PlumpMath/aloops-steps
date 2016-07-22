(defproject oscp5 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "2.4.0"]
                 [de.sojamo/oscp5 "0.9.8"]] ;; Por qué a Juan le funciona sin incluir esto en el project.clj?
  :main oscp5.osc01
  :aot [oscp5.osc01])
