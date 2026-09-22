---
description: "Jenkinsfile и ci.yml повторяют одни шаги — не должны расходиться (ADR-0004)"
trigger: glob
globs: ["Jenkinsfile", ".github/workflows/**"]
---

# Синхронность CI-конфигов

- Канонический список шагов и их порядок — `.github/workflows/ci.yml`.
- `Jenkinsfile` повторяет эти шаги, а не выдумывает свои (ADR-0004).
- Меняешь один файл — проверь и поправь второй: расхождение ломает
  один из двух CI.
- Различия допустимы только в механике: агент `java-jdk21`, браузер
  из Selenoid и страница с GitHub Pages через `JAVA_TOOL_OPTIONS`,
  Allure-плагин вместо шага `generate` — см. `docs/kb/jenkins.md`.
