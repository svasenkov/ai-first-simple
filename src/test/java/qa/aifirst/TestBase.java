package qa.aifirst;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;

class TestBase {

    // Открываем index.html из корня проекта по file:// — веб-сервер не нужен
    static final String BASE_URL = new File("index.html").toURI().toString();

    @BeforeAll
    static void setUp() {
        // Без открытия окна браузера — так тест работает и на CI без дисплея
        Configuration.headless = true;
        // Лисенер прикрепляет скриншот страницы к каждому шагу в Allure-отчёте
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));
    }
}
