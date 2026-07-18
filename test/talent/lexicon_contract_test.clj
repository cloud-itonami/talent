(ns talent.lexicon-contract-test
  (:require [clojure.edn :as edn]
            [clojure.test :refer [deftest is]]))

(defn- read-edn [path] (edn/read-string (slurp path)))

(deftest canonical-talent-lexicons
  (doseq [[path expected]
          [["lex/talentProfile.edn" "com.etzhayyim.talent.talentProfile"]
           ["lex/talentCohort.edn" "com.etzhayyim.talent.talentCohort"]]]
    (let [document (read-edn path)]
      (is (= 1 (:lexicon document)) path)
      (is (= expected (:id document)) path)
      (is (map? (:defs document)) path))))

(deftest hard-delete-and-cohort-schema-invariants
  (let [domain-schema (read-edn "kotoba/schema.edn")
        lex-schema (read-edn "schema.edn")
        domain-idents (set (map :db/ident domain-schema))
        lex-idents (set (map :db/ident lex-schema))]
    (is (not (contains? domain-idents :profile/alive)))
    (is (not (contains? domain-idents :profile/deleted-at)))
    (is (contains? domain-idents :profile/enc-pii))
    (is (contains? domain-idents :cohort/count))
    (is (= 6 (count lex-idents)))))
