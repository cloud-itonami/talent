(ns talent.repository-contract-test
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is]]))

(defn- read-edn [path] (edn/read-string (slurp path)))

(deftest canonical-repository-shape
  (doseq [path ["manifest.edn" "identity.edn" "dependencies.edn"
                "repository-contracts.edn" "schema.edn" "kotoba/schema.edn"
                "lex/talentProfile.edn" "lex/talentCohort.edn"]]
    (is (some? (read-edn path)) path))
  (is (= "talent" (:actor/id (read-edn "manifest.edn"))))
  (is (= ["isco" "recruit"] (:actor/integrates (read-edn "manifest.edn"))))
  (is (not (.exists (io/file "actor-manifest.jsonld"))))
  (is (not (.exists (io/file "run_tests.sh"))))
  (is (not (.exists (io/file "py/requirements.txt"))))
  (is (.exists (io/file "wire/actor-manifest.jsonld"))))

(deftest dependencies-are-immutable-flat-west-references
  (let [deps (:dependencies (read-edn "dependencies.edn"))]
    (is (= #{'etzhayyim/root 'com.etzhayyim/isco 'com.etzhayyim/recruit}
           (set (map :dependency/id deps))))
    (is (every? #(re-matches #"[0-9a-f]{40}" (:dependency/revision %)) deps))
    (is (= #{"orgs/etzhayyim/com-etzhayyim-isco"
             "orgs/etzhayyim/com-etzhayyim-recruit"}
           (set (keep :dependency/west-path deps))))))
