(ns query-manager.sql
  (:refer-clojure :exclude [replace find])
  (:require [clojure.tools.logging :refer [info]]
            [clojure.string :refer [replace]]))

(defrecord Query [id sql description])

(def ^:private query-db (atom {}))

(let [id (atom 0)]
  (defn- id-gen [] (str (swap! id inc))))

(defn all
  []
  (or (vals @query-db) []))

(defn one
  [query-id]
  (get @query-db query-id))

(defn create!
  [sql description]
  (let [q (Query. (id-gen) sql description)]
    (swap! query-db assoc (:id q) q)))

(defn delete!
  [query-id]
  (swap! query-db dissoc query-id))

(defn update!
  [query]
  (swap! query-db assoc (:id query) query))
