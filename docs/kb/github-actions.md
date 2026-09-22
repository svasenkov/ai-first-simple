# GitHub Actions: как устроен CI

## Где лежит workflow?

`.github/workflows/ci.yml`, имя workflow — `CI`. Бейдж статуса
в шапке `README.md` ссылается на `actions/workflows/ci.yml`.

## Когда запускается?

Триггеры в `on:` — `push` в `main` и `pull_request` в `main`.
Пуш в фича-ветку без PR CI не запускает.
`permissions: contents: read` — workflow работает с минимальными
правами, писать в репозиторий не может.

## Какие шаги в job `test`?

`runs-on: ubuntu-latest`, шаги по порядку:

1. `actions/checkout` — клонирование репозитория;
2. `actions/setup-java` — Java Temurin (версия — в ci.yml), `cache: gradle`;
3. `./gradlew test` — тесты, сырые результаты в `build/allure-results`;
4. скачивание Allure CLI: `curl` архива с Maven Central,
   проверка `sha256sum -c`, `unzip` в `/tmp/allure-cli`;
5. `allure generate build/allure-results -o build/allure-report`;
6. `actions/upload-artifact` — публикация `build/allure-report`
   как артефакт `allure-report`.

Шаги 4–6 идут с `if: always()` — отчёт собирается и публикуется
даже при упавших тестах (в нём и будут скриншоты падения).

## Почему экшены указаны как `@<sha>`?

Каждый экшен pinned полным SHA коммита с комментарием версии —
формат `actions/<name>@<sha> # vX.Y.Z`, актуальные значения в ci.yml.
Тег можно перезаписать, SHA — нет: это защита от supply-chain подмены
экшена. При обновлении меняют и SHA, и комментарий.

## Почему версия Allure CLI отличается от версии адаптеров?

Патч-релиз адаптеров (allure-junit5, allure-selenide) может выходить
без синхронного релиза CLI. Поэтому версии хранятся раздельно:
адаптеры — в `build.gradle`, CLI — `ALLURE_VERSION` + `ALLURE_SHA256`
в `env` ci.yml (комментарий над `env`). При обновлении проверяй,
что выбранная версия CLI опубликована на Maven Central.

## Где смотреть логи и отчёт?

Вкладка **Actions** → нужный прогон → job `test` — там логи всех
шагов, включая вывод `./gradlew test`. Готовый отчёт — в блоке
**Artifacts** внизу страницы прогона: `allure-report` (zip →
`index.html`). Устройство отчёта — `docs/kb/allure.md`.
