(ns oscp5-fun-mode.core
  (:import [oscP5 OscP5 OscMessage]
           [netP5 NetAddress])
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(declare my-remote-location my-oscP5)

(def in-port 12000)
(def out-port 12000)

(def bg [25 25 25])
;;(def bg-color (atom bg))

;; The setup function takes no argument and returns the initial state of the application.
;; Thus there is no need to use set-state!

(defn setup []
  ;; initial state
  {:bg-color bg})

;; No need of update function in this example
;; (defn update [state]   )

(defn draw [state]
  (apply q/background (:bg-color state)))

(defn make-osc-message [path]
  (OscMessage. path))

(defn send-osc-message [message]
    (.send my-oscP5 message my-remote-location))

;; mouse-clicked takes the old-state and an event
;; In this example we don't use mouse-clicked to modify the state,
;; just to send an osc message
;; Thus, there's no need to use event, and the state is returned unchanged.
(defn mouse-clicked [state event]
  (-> (make-osc-message "/test")
      (.add "123")
      (send-osc-message))
  state)

(defn alter-bg-color []
  (map #(* % (rand-int 10)) bg))

(defn osc-event [state message]
  (println "mensage recibido en la aplicación: " message)
  (println (.addrPattern message))
  ;; (reset! bg-color (map #(* % (rand-int 10)) bg))
  (-> state
      (assoc :bg-color (alter-bg-color))))

(q/defsketch papplet
             :title "osc"
             :setup setup
             :draw draw
             :size [200 200]
             :mouse-clicked mouse-clicked
             :osc-event osc-event
             :features [:keep-on-top
                        :exit-on-close
                        :no-bind-output]
             :middleware [m/fun-mode])

(defn init-oscP5-communication [papplet]
  (def my-oscP5 (OscP5. papplet in-port))
  (def my-remote-location (NetAddress. "localhost" out-port)))

(init-oscP5-communication papplet)

(defn -main [& args] )


