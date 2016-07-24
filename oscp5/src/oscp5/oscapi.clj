(ns oscp5.oscapi
  (:import [oscP5 OscP5 OscMessage ]
           [netP5 NetAddress])
  (:require [oscp5.osc :as o]))

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
     "/live/tempo" :tempo
     (do (println "OSC-EVENT NOT FILTERED" path)))))

(defn read-clip-info [osc-message]
  (let [[track clip nombre] (.arguments osc-message)]
    {:track track :clip clip :nombre nombre}))

(defn process-osc-event [message]
  (let [osc-event (event-to-keyword message)]
    (condp = osc-event
      :clip (println ":clip")
      :info (println ":info")
      :play (println ":play")
      :volume (println ":volume")
      (do (println "not mapped"))) ;; Important: If no default expression is provided and no clause matches, an
                                   ;; IllegalArgumentException is thrown.
      ))

(defn async-request-info-for-all-clips []
    (o/send-osc-message (o/make-osc-message "/live/name/clip")))

(defn init-oscP5-communication [papplet]
  (o/init-oscP5 papplet))

























