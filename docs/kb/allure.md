# Allure: результаты и отчёт

## Чем `allure-results` отличается от `allure-report`?

`build/allure-results/` — сырые результаты прогона: xml-файл на
каждый тест + вложения (скриншоты `*-attachment.png`). Это вход
для генератора, а не отчёт для чтения.

`build/allure-report/` — сгенерированный статический html-отчёт:
его открывают в браузере (`index.html` внутри).

## Куда складываются результаты?

Путь задан в `build.gradle` в блоке `test`:
`systemProperty 'allure.results.directory', "${buildDir}/allure-results"`.
Каталог живёт в `build/` — чистится `./gradlew clean`, в git не
попадает (`.gitignore`).

## `allure serve` vs `allure generate` — что когда?

- `allure serve build/allure-results` — локально: собирает отчёт во
  временный каталог и сразу открывает в браузере. Нужен установленный
  Allure CLI.
- `allure generate build/allure-results -o build/allure-report` —
  собирает html в каталог, ничего не открывая. Так делает CI
  (`.github/workflows/ci.yml`), затем каталог уходит в артефакт.
- `allure open build/allure-report` — открыть уже сгенерированный
  отчёт через встроенный сервер (надёжнее, чем file://, — отчёт
  подгружает данные через fetch).

## Откуда скриншоты в отчёте?

Лисенер `allure-selenide` подключён в `TestBase.setUp()`:
`SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true))`.
Он прикрепляет скриншот страницы к каждому шагу, включая упавший.
Версии адаптеров — в `build.gradle` (`allure-junit5`,
`allure-selenide`); версия CLI в CI — `docs/kb/github-actions.md`.

## Тест упал — где искать причину?

1. Stacktrace и локатор — в выводе `./gradlew test`.
2. Скриншот на момент падения — `*-attachment.png` в
   `build/allure-results/` или в шаге упавшего теста в html-отчёте.
3. В CI — в артефакте `allure-report` прогона Actions.

Полная процедура разбора — skill `.devin/skills/run-tests/SKILL.md`.

## Как открыть отчёт из CI?

Actions → прогон → **Artifacts** → скачать `allure-report` →
распаковать zip → открыть `index.html` (README) либо
`allure open` в распакованном каталоге.
