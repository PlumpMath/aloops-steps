(ns oscp5-send-receive.core
  (:import [oscP5 OscP5 OscMessage]
           [netP5 NetAddress])
  (:require [quil.core :as q]))

(declare my-remote-location my-oscP5)

(def in-port 12000)
(def out-port 12000)

(def bg [25 25 25])
(def bg-color (atom bg))

(defn setup [])

(defn draw []
  (apply q/background @bg-color))

(defn make-osc-message [path]
  (OscMessage. path))

(defn send-osc-message [message]
    (.send my-oscP5 message my-remote-location))

(defn mouse-clicked []
  (-> (make-osc-message "/test")
      (.add "123")
      (send-osc-message)))

(defn osc-event [message]
  (println "mensage recibido en la aplicación: " message)
  (println (.addrPattern message))
  (reset! bg-color (map #(* % (rand-int 10)) bg)))

(q/defsketch papplet
             :title "osc"
             :setup setup
             :draw draw
             :size [200 200]
             :mouse-clicked mouse-clicked
             :osc-event osc-event
             :features [:keep-on-top
                        :exit-on-close
                        :no-bind-output])

(defn init-oscP5-communication [papplet]
  (def my-oscP5 (OscP5. papplet in-port))
  (def my-remote-location (NetAddress. "localhost" out-port)))

(init-oscP5-communication papplet)

(defn -main [& args] )
