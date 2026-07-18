(ns talent.social-test
  (:require [clojure.test :refer [deftest is]]
            [talent.cells.social-post.state-machine :as cell]
            [talent.methods.social :as social]))

(deftest dry-run-social-projection
  (let [post (social/draft-observation-post
              "ISCO cohort" "k-anonymous cohort observation" ["source-a" "source-b"])]
    (is (= ":dry-run" (get post ":post/status")))
    (is (true? (get post ":post/is-mirror")))
    (is (false? (get post ":post/server-held-key")))
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"Council Lv6"
                          (social/build-live {})))))

(deftest social-boundaries-refuse
  (is (thrown-with-msg? clojure.lang.ExceptionInfo #"needs ≥ 2"
                        (social/draft-observation-post "x" "y" ["one"])))
  (is (= cell/phase-refused
         (get-in (cell/transition-to-drafted
                  {"sources" ["a" "b"] "requested_status" "published"})
                 ["cell_state" "phase"])))
  (is (= cell/phase-refused
         (get-in (cell/transition-to-drafted
                  {"sources" ["a" "b"] "server_held_key" true})
                 ["cell_state" "phase"])))
  (is (= cell/phase-drafted
         (get-in (cell/transition-to-drafted
                  {"subject" "cohort" "sources" ["a" "b"]})
                 ["cell_state" "phase"]))))
