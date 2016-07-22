(ns oscp5.osc01
  (:import [oscP5 OscP5 OscMessage ]
           [netP5 NetAddress])
  (:require [quil.core :as q]
            [quil.applet :as qa])
  (:gen-class)
  )

(def in-port 9001)

;(def out-port 9000)

(def bg-color [255 180 250])

(defn setup []
  )

(defn draw []
  (apply q/background [255 180 250])
  )

(defn substring? [sub st]
  (if (not= (.indexOf st sub) -1)
    true
    false))

(defn event-to-keyword [message]
  (let [path (.addrPattern message)]
   (condp substring?  path
     "/live/name/clip" :clip
     "/live/clip/info" :info
     "/live/play" :play
     "/live/clip/loopend" :loopend
     "/live/volume" :volume
     "/live/solo" :solo
     "/live/tempo" :tempo)))

(defn read-clip-info [osc-message]
  (let [[track clip nombre] (.arguments osc-message)]
    {:track track :clip clip :nombre nombre}))

(defn process-osc-event [message]
  (let [osc-event (event-to-keyword message)]
    (condp = osc-event
      :clip (do
              (q/text (:track (read-clip-info message)) 10 10)
              (println :clip)
              )
      :info (println :info)
      :play (println :play)
      :volume (println :volume))
      ))



(q/defsketch papplet
             :title "beat"
             :setup setup
             :draw draw
             :size [200 200]
             :osc-event process-osc-event
             :features [:keep-on-top])

(def my-oscP5 (OscP5. papplet in-port))

;(defn make-osc-message [path]
;  (OscMessage. path))
;(defn send-osc-message [message]
; (.send my-oscP5 message my-remote-location))





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
