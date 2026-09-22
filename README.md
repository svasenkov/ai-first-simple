# ai-first-simple

Учебный репозиторий «AI-first QA»: минимальный продукт + один автотест.

## Продукт

`index.html` — форма логина. Валидные креды: `admin` / `admin123`.
Страница открывается напрямую через `file://` — веб-сервер не нужен.

## Тесты

Стек: Java 21, Gradle Wrapper, JUnit 5, Selenide, Allure.

Gradle Wrapper хранится в git, установленный Gradle не нужен —
на чистом клоне просто запускайте тесты:

```bash
./gradlew test
```

Сырые результаты Allure складываются в `build/allure-results/`.
Посмотреть отчёт (если установлен Allure CLI):

```bash
allure serve build/allure-results
```
