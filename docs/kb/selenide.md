# Selenide: как устроены тесты

## Где лежат тесты и конфиг?

Тесты — в `src/test/java/qa/aifirst/` (`TestBase.java`, `LoginTest.java`).
Версии стека (Java, JUnit, Selenide, Allure) — в `build.gradle`
(`toolchain` и `dependencies`), здесь не дублируются.

## Зачем TestBase и что в нём?

`TestBase` — общий базовый класс: `LoginTest extends TestBase`.
В `@BeforeAll setUp()` две настройки:

- `Configuration.headless = true` — браузер без окна, тест работает
  на CI без дисплея;
- `SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true))` —
  лисенер прикрепляет скриншот к шагам в Allure (см. `allure.md`).

Не дублируй эту настройку в тестах — конвенция зафиксирована
в `.devin/rules/tests.md`.

## Как тест открывает страницу без сервера?

`BASE_URL = new File("index.html").toURI().toString()` в `TestBase` —
форма открывается по `file://` прямо из корня проекта, веб-сервер
не нужен. В тесте: `open(BASE_URL)`.

## Как выглядят шаги теста?

Selenide-стиль: `$("css")` находит элемент, `.setValue()/.click()` —
действия, `.shouldHave(text("..."))` — проверка. Весь сценарий
из `LoginTest.successfulLogin`:

```java
open(BASE_URL);
$("#username").setValue("admin");
$("#password").setValue("admin123");
$("button[type=submit]").click();
$("#message").shouldHave(text("Вход выполнен успешно"));
```

Локаторы и тексты берутся из `index.html` (`#username`, `#password`,
`button[type=submit]`, `#message`), валидные креды — `admin`/`admin123`.

## Где ожидания? Почему нет sleep/wait?

Явных ожиданий нет и не нужно: Selenide сам ждёт элементы и условия
до таймаута (по умолчанию 4 с) — `shouldHave`, `click`, `setValue`
уже «умные». `Thread.sleep` и `WebDriverWait` в проекте не используются.

## Как запустить?

`./gradlew test` — Gradle Wrapper в git, установленный Gradle не нужен.
Драйвер браузера скачивает Selenium Manager автоматически.

## Как добавить новый тест?

Класс в `src/test/java/qa/aifirst/`, наследование от `TestBase`,
один метод = один сценарий, `@DisplayName` на русском. Процедура —
skill `.devin/skills/add-ui-test/SKILL.md`.
