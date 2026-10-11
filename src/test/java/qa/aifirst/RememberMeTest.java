package qa.aifirst;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class RememberMeTest extends TestBase {

    private static final String PAGE = "https://augorbunov.github.io/ai-first-simple/index.html";

    @Test
    @DisplayName("Чекбокс «Запомнить меня» существует и кликабелен")
    void rememberMeCheckboxExistsAndIsClickable() {
        open(PAGE);
        $("label.remember").shouldHave(text("Запомнить меня"));
        $("#remember-me").should(exist).shouldBe(clickable);
        $("#remember-me").click();
        $("#remember-me").shouldBe(checked);
    }
}
