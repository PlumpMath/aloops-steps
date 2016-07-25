(ns oscp5-uberjar.core
  (:import [oscP5 OscP5 OscMessage ]
           [netP5 NetAddress])
  (:require [quil.core :as q]
            [oscp5-uberjar.oscapi :as oscapi])
  (:gen-class))


(def bg-color [40 40 40])

(def path (atom "hola"))

(defn setup []  )

(defn draw []
  (apply q/background bg-color)
  (q/text @path 30 30))

(defn mouse-clicked [] (do
                         (println "mouse clicked")
                         (oscapi/async-request-info-for-all-clips)))

(defn osc-event [message]
  (println "mensage recibido en la aplicación: " message)
  (reset! path (.addrPattern message)))

(defn -main [& args]
  (oscapi/init-oscP5-communication (q/sketch
                                     :title "osc"
                                     :setup setup
                                     :draw draw
                                     :size [200 200]
                                     :mouse-clicked mouse-clicked
                                     :osc-event osc-event
                                     :features [:keep-on-top
                                                :exit-on-close
                                                :no-bind-output])))
