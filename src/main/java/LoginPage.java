import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.shadowCss;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    private final String EMAIL = "[name=email]";
    private final String PASSWORD = "[name=password]";
    private final By SIGN_IN_BUTTON = byText("Sign in");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Вход в систему с логином:'{user}' и паролем: '{password}'")
    public ProjectsListPage login(String user, String password) {
        open("/login");
        $(shadowCss("#accept", "#usercentrics-cmp-ui")).click();
        $(EMAIL).setValue(user);
        $(PASSWORD).setValue(password);
        $(SIGN_IN_BUTTON).click();
        return new ProjectsListPage(driver);
    }
}