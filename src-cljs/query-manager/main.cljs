(ns query-manager.main
  (:use-macros [dommy.macros :only [sel1]])
  (:require [dommy.core :refer [replace-contents! listen! append!]]

            ;; Events
            [query-manager.event :as event]

            ;; Views
            [query-manager.view.status-bar :as status-bar]
            [query-manager.view.title-bar :as title-bar]
            [query-manager.view.db-panel :as db-panel]
            [query-manager.view.db-form :as db-form]
            [query-manager.view.job-panel :as job-panel]
            [query-manager.view.upload-panel :as upload-panel]
            [query-manager.view.query-panel :as query-panel]
            [query-manager.view.error-panel :as error-panel]

            ;; Processes
            [query-manager.proc.clock :as clock]
            [query-manager.proc.job-monitor :as job-monitor]

            ;; Network
            [query-manager.net :as net]))

;;-----------------------------------------------------------------------------
;; Main
;;-----------------------------------------------------------------------------

(defn main
  []
  (.log js/console "loading")

  ;; Composite UI components
  (replace-contents! (sel1 :body) (title-bar/dom))
  (append! (sel1 :body)
           (db-form/dom event/broadcast)
           (db-panel/dom event/broadcast)
           (job-panel/dom event/broadcast)
           (query-panel/dom event/broadcast)
           (upload-panel/dom event/broadcast)
           (error-panel/dom event/broadcast)
           (status-bar/dom))

  ;; Register UI event subscriptions
  (event/map-subs status-bar/recv (status-bar/events))
  (event/map-subs title-bar/recv (title-bar/events))
  (event/map-subs db-panel/recv (db-panel/events))
  (event/map-subs db-form/recv (db-form/events))
  (event/map-subs job-panel/recv (job-panel/events))
  (event/map-subs upload-panel/recv (upload-panel/events))
  (event/map-subs query-panel/recv (query-panel/events))
  (event/map-subs error-panel/recv (error-panel/events))

  ;; Register non-UI event subscribers
  (event/map-subs net/recv (net/events))

  ;; Just for fun, for now.
  (listen! (sel1 :html) :mousemove
           (fn [e]
             (let [x (.-clientX e)
                   y (.-clientY e)]
               (event/broadcast [:mousemove {:value [x y]}]))))

  ;; Start background processes
  (clock/start event/broadcast)
  (job-monitor/start event/broadcast)

  ;; Init
  (event/broadcast [:db-poke {}])
  (event/broadcast [:query-poke {}])
  (event/broadcast [:jobs-poke {}])

  (.log js/console " - loaded"))

(set! (.-onload js/window) main)
