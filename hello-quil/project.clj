(defproject hello-quil "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "2.4.0"]]
  :main hello-quil.spinning-circle)

;; Aunque no vayamos a convertir el archivo a jar, si incluimos :main
;; podemos correr el archivo desde la ventana de comandos, llamando a "lein run"
;; No es necesario en este caso incluir :aot

