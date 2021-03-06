(ns query-manager.proc.clock
  (:require [query-manager.utils :refer [spawn-after!]]))

;;-----------------------------------------------------------------------------
;; Implementation
;;-----------------------------------------------------------------------------

(defn- start-clock
  [broadcast]
  (spawn-after! 1000 (fn []
                       (broadcast [:clock {:value (.getTime (js/Date.))}])
                       (start-clock broadcast))))

;;-----------------------------------------------------------------------------
;; Interface
;;-----------------------------------------------------------------------------

(defn start
  [broadcast]
  (start-clock broadcast))
