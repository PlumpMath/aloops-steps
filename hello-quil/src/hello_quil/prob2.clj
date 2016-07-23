(ns hello-quil.prob2
  (:require [quil.core :as q])
  (:gen-class))

(defn setup []
  (q/frame-rate 1)
  (q/background 200))

(defn draw []
  (q/stroke (q/random 255) (q/random 255) (q/random 255))
  (q/stroke-weight (q/random 10))
  (q/fill (q/random 255) (q/random 255) (q/random 255))

  (let [diam (q/random 100)
        x    (q/random 100 400)
        y    (q/random 100 300)]
    (q/ellipse x y diam diam)))

(defn run-sketch []
   (q/defsketch applet
    :title "Oh so many grey circles"
    :settings #(q/smooth 2)
    :setup setup
    :draw draw
    :size [500 400]
    ))

;;defsketch can be inside a function, and then this function is called from -main

(defn -main [& args]  (run-sketch))

(-main)
