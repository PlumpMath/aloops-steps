(ns oscp5.main
  (:import [oscP5 OscP5 OscMessage ]
           [netP5 NetAddress])
  (:require ;[quil.core :as q]
            ;[quil.applet :as qa]
            [oscp5.oscapi :as oscapi])
  (:use quil.core)
  ;(:gen-class)
  )


(def bg-color [255 180 250])

(defn setup []  )

(defn draw []
  (apply background [255 180 250])   )

(defn mouse-clicked [] (do
                         (println "mouse clicked")
                         (oscapi/async-request-info-for-all-clips)))

(defn osc-event [] (println "*************osc-event******************"))

(defsketch papplet
             :title "osc"
             :setup setup
             :draw draw
             :size [200 200]
             :mouse-clicked mouse-clicked
             :osc-event osc-event
             :features [:keep-on-top
                        :exit-on-close])

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
