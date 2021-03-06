(ns query-manager.view.query-panel
  (:use-macros [dommy.macros :only [sel1 sel node]])
  (:require [dommy.core :refer [toggle! attr listen! replace-contents!]]
            [query-manager.utils :refer [flash! listen-all!]]
            [clojure.string :as string]))

;;-----------------------------------------------------------------------------
;; Implementation
;;-----------------------------------------------------------------------------

(defn- template
  []
  (node [:div#queries.panel
         [:h2 "Queries"]
         [:div#queries-table.lister
          [:p "No queries defined."]
          [:button#qp-new "new"]]]))

(defn- sql-of
  [sql]
  (-> (string/replace sql #"\s+" " ")
      (subs 0 50)
      (string/lower-case)
      (string/trim)
      (str "...")))

(defn- table-of
  [queries]
  (node (list [:table
               [:tr
                [:th {:width "40%"} "desc"]
                [:th {:width "45%"} "sql"]
                [:th.actions {:width "15%"} "actions"]]
               (for [q queries]
                 [:tr {:id (str "qp-row-" (:id q))}
                  [:td (:description q)]
                  [:td (sql-of (:sql q))]
                  [:td.actions
                   [:button.qp-run {:qid (:id q)} "run"]
                   [:button.qp-edit {:qid (:id q)} "edit"]
                   [:button.qp-del {:qid (:id q)} "del"]]])]
              [:button#qp-new "new"]
              [:button#qp-runall "run all"])))

(defn- on-run-all
  [broadcast qids]
  (fn [e]
    (doseq [qid qids]
      (broadcast [:query-run {:value qid}]))))

(defn- on-run
  [broadcast]
  (fn [e]
    (let [id (attr (.-target e) :qid)]
      (flash! (sel1 (keyword (str "#qp-row-" id))) :flash)
      (broadcast [:query-run {:value id}]))))

(defn- on-delete
  [broadcast]
  (fn [e]
    (let [id (attr (.-target e) :qid)
          row (keyword (str "#qp-row-" id))]
      (flash! (sel1 row) :flash)
      (broadcast [:query-delete {:value id}]))))

(defn- on-new
  [broadcast]
  (fn [e]
    (broadcast [:query-form-show {}])))

(defn- on-edit
  [broadcast]
  (fn [e]
    (let [id (attr (.-target e) :qid)]
      (flash! (sel1 (keyword (str "#qp-row-" id))) :flash)
      (broadcast [:query-poke {:value id}])
      (broadcast [:query-form-show {}]))))

(defn- on-query-change
  [broadcast queries]
  (if (empty? queries)

    (do (replace-contents! (sel1 :#queries-table)
                           (node (list [:p "No queries defined."]
                                       [:button#qp-new "new"])))
        (listen! (sel1 :#qp-new) :click (on-new broadcast)))

    (let [table (table-of (sort-by :id queries))]
      (replace-contents! (sel1 :#queries-table) table)
      (listen-all! (sel :.qp-run) :click (on-run broadcast))
      (listen-all! (sel :.qp-edit) :click (on-edit broadcast))
      (listen-all! (sel :.qp-del) :click (on-delete broadcast))
      (listen! (sel1 :#qp-new) :click (on-new broadcast))
      (listen! (sel1 :#qp-runall) :click (on-run-all broadcast (map :id queries))))))

(defn- on-visibility-toggle!
  [broadcast]
  (toggle! (sel1 :#queries)))

(defn- mk-template
  [broadcast]
  (template))

;;-----------------------------------------------------------------------------
;; Interface
;;-----------------------------------------------------------------------------

(defn dom
  [broadcast]
  (mk-template broadcast))

(defn events
  []
  [:query-change :query-panel-toggle])

(defn recv
  [broadcast [topic event]]
  (case topic
    :query-change (on-query-change broadcast (:value event))
    :query-panel-toggle (on-visibility-toggle! broadcast)
    true))
