# DEPRECATED WIRE: `wire/actor-manifest.jsonld`

The legacy JSON-LD wire document (T1 MCP-Compose) persists `TalentProfile`/`TalentCohort` the
pre-kotoba way (RisingWave-via-Hyperdrive) and expresses the PII-Tier-3 rules as manifest text,
which **violates the substrate boundary** (kotoba EAVT only).

**Canonical manifest is now `manifest.edn`** (kotoba-native), per **ADR-2606072600** — Phase A of
the substrate remediation wave (ADR-2606071800). The four privacy rules are now structural in
`src/talent/methods/agent.cljc` plus the canonical Clojure suite: self-sovereign write (caller = subject), Signal-E2E PII
(plaintext refused), k-anonymity cohort stats, GDPR Art 17 hard delete. See `kotoba/schema.edn`.

Retained one R-cycle for reference, then removed. Do not extend it.
