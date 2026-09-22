package qa.aifirst;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

class TestBase {

    @BeforeAll
    static void setUp() {
        // Без открытия окна браузера — так тест работает и на CI без дисплея
        Configuration.headless = true;
        // Лисенер прикрепляет скриншот страницы к каждому шагу в Allure-отчёте
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));
    }
}
