import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.text;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage extends BasePage {

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страница проекта '{projectName}'")
    public ProjectPage openPage(String projectName) {
        driver.get("https://app.qase.io/project/" + projectName);
        return this;
    }

    @Step("Загрузка страницы проекта '{projectName}'")
    public ProjectPage isPageLoaded(String projectName) {
        $x("//*[@id='application-content']//h1").shouldHave(text(projectName + " repository"));
        return this;
    }

    @Step("Получение названия созданного проекта")
    public String getProjectName() {
        return $x("//*[@id='application-content']//h1").getText();
    }
}