(ns freergit.clojure-bbo
  (:gen-class)
  (:require
   [manifold.deferred :as d]
   [clj-commons.byte-streams :as bs]
   [aleph.http :as http]
   [manifold.stream :as s]))

(defn greet
  "Callable entry point to the application."
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (greet {:name (first args)}))

(-> @(http/get "https://google.com/")
    :body
    bs/to-string
    prn)


(def sub "{
    \"op\": \"subscribe\",
    \"args\": [\"orderbook.1.BTCUSDT\"]}")

(defn idk []
  (let [conn @(http/websocket-client "wss://stream.bybit.com/v5/public/spot")]
    (s/put! conn sub)
    (prn "hh")
    (->> conn
         bs/print-bytes)
    (prn "hej")))

(idk)
