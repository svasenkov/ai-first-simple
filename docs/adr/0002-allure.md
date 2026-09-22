# 0002. Allure для отчётов о прогоне

Дата: 2026-09-21
Статус: принято

## Context

Результат прогона должны читать люди: студент локально и
ревьюер в CI. Стандартный выход Gradle — сырые JUnit XML
(`build/test-results`): машиночитаемый формат без скриншотов
и шагов, глазами разбирать неудобно. Альтернатива — оставить
только эти XML и stacktrace в консоли.

## Decision

Подключить Allure:

- адаптеры `allure-junit5` и `allure-selenide` в `build.gradle`;
- лисенер `AllureSelenide().screenshots(true)` в `TestBase` —
  скриншот страницы прикрепляется к шагам, включая упавший;
- локально — `allure serve build/allure-results`;
- в CI — `allure generate` → html уходит артефактом
  `allure-report` (шаги с `if: always()` в
  `.github/workflows/ci.yml`).

Устройство результатов и отчёта — `docs/kb/allure.md`.

## Consequences

Понятный html-отчёт со скриншотом на момент падения, доступный
и локально, и артефактом в CI. Цена — лишние зависимости и шаг
скачивания CLI в CI (версия и sha256 pinned). Отчёт не «из
коробки» как XML — его нужно генерировать.
