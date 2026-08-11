# talent — cohort-first talent registry

`talent` is the self-sovereign inverse of a recruiter database. Individuals register their own
ISCO-08 profile, identifying fields remain Signal-E2E ciphertext, public reads expose only
k-anonymous cohort aggregates, and `forget-self` performs a hard GDPR Article 17 deletion.

This executable workforce-classification actor is owned by `cloud-itonami/talent`. Reusable ISCO
source data remains separate in `cloud-itonami/org-ilo-isco`, while `cloud-itonami/isco` owns the
classification coordinator. The existing `did:web:*.etzhayyim.com`, AT Protocol, and Radicle
identities remain compatibility identifiers; the repository move does not mint a new actor.

Repository truth is EDN and CLJC:

- `manifest.edn`, `identity.edn`, and `dependencies.edn` define the actor boundary.
- `lex/` and `schema.edn` contain canonical lexicon contracts.
- `kotoba/schema.edn` defines the profile/cohort Datom schema.
- `src/talent/` contains registry and dry-run social behavior.
- `wire/` contains deprecated JSON-LD interoperability only.

Run the standalone suite with:

```sh
clojure -M -e '(load-file "run_tests.clj")'
```

`isco` and `recruit` are exact-SHA dependencies at their flat west paths under
`orgs/cloud-itonami`; no source reaches into the former numbered root tree.
