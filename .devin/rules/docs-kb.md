---
description: "Дисциплина ссылок в docs/: INDEX.md, нумерация ADR, карта AGENTS.md"
trigger: glob
globs: ["docs/**"]
---

# Дисциплина ссылок в docs/

- Новый файл в `docs/kb/` → добавь строку в `docs/kb/INDEX.md`,
  иначе его не найдут.
- Новый ADR → следующий номер по порядку, шаблон
  `docs/adr/0000-template.md`, ≤ 60 строк (skill `write-adr`).
- Удалил или переименовал файл, на который ссылается карта в
  `AGENTS.md`, — поправь карту.
