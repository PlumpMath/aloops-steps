(ns oscp5.main
  (:import [oscP5 OscP5 OscMessage ]
           [netP5 NetAddress])
  (:require [quil.core :as q]
            [oscp5.oscapi :as oscapi]))


(def bg-color [25 25 25])

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

(oscapi/init-oscP5-communication papplet)

(defn -main [& args] )















#_((ns mapping(:import [de.fhpotsdam.unfolding UnfoldingMap]
                    [de.fhpotsdam.unfolding.utils MapUtils])
  (:require [quil.core :as q]
            [quil.applet :as qa]))

(defn setup []
  (q/set-state!
   :map (UnfoldingMap. (qa/current-applet)))
  (MapUtils/createDefaultEventDispatcher (qa/current-applet) (q/state :map)))

(defn draw []
  (.draw (q/state :map)))

(q/defsketch mapping
  :title "Exploring Unfolding"
  :setup setup
  :draw draw
  :size [700 1000]))
