package qa.aifirst;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class LoginErrorTest extends TestBase {

    private static final String PAGE = "https://augorbunov.github.io/ai-first-simple/index.html";
    private static final String ERROR = "Неверное имя пользователя или пароль";
    private static final String EMPTY_USERNAME = "Введите имя пользователя";
    private static final String EMPTY_PASSWORD = "Введите пароль";
    private static final String EMPTY_BOTH = "Введите имя пользователя и пароль";

    @Test
    @DisplayName("Неверная пара логин/пароль показывает сообщение об ошибке")
    void invalidCredentialsShowError() {
        open(PAGE);
        $("#username").setValue("admin");
        $("#password").setValue("wrong");
        $("button[type=submit]").click();
        $("#message").shouldHave(text(ERROR));
    }

    @Test
    @DisplayName("Пустое имя пользователя показывает сообщение об ошибке")
    void emptyUsernameShowsError() {
        open(PAGE);
        $("#password").setValue("admin123");
        $("button[type=submit]").click();
        $("#message").shouldHave(text(EMPTY_USERNAME));
    }

    @Test
    @DisplayName("Пустой пароль показывает сообщение об ошибке")
    void emptyPasswordShowsError() {
        open(PAGE);
        $("#username").setValue("admin");
        $("button[type=submit]").click();
        $("#message").shouldHave(text(EMPTY_PASSWORD));
    }

    @Test
    @DisplayName("Пустые имя пользователя и пароль показывают сообщение об ошибке")
    void emptyUsernameAndPasswordShowError() {
        open(PAGE);
        $("button[type=submit]").click();
        $("#message").shouldHave(text(EMPTY_BOTH));
    }
}
