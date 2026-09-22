---
description: "index.html связан с тестами: локаторы, креды и тексты формы"
trigger: glob
globs: ["index.html"]
---

# Продукт связан с тестами

- `#username`, `#password`, `button[type=submit]`, `#message`,
  креды `admin`/`admin123` и тексты сообщений используются в
  `src/test/**` — меняя форму, обнови тесты.
- Если после правки `index.html` тест упал — сначала уточни у
  пользователя, это баг продукта или теста (см. skill `run-tests`).
