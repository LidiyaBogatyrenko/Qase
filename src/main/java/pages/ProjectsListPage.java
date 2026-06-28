package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProjectsListPage extends BasePage {

    public ProjectsListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Загрузка страница Projects")
    public ProjectsListPage isPageOpened() {
        $(byText("Projects")).shouldBe(visible);
        $(byText("Create new project")).shouldBe(visible);
        return this;
    }

    @Step("Создание нового проекта с названием: '{projectName}'")
    public ProjectsListPage createNewProject(String projectName, String projectCode) {
        $(byText("Create new project")).click();
        $("#project-name").setValue(projectName);
        $("#project-code").setValue(projectCode);
        return this;
    }

    @Step("Нажатие на кнопку Create project в модальном окне")
    public ProjectPage clickCreateProject() {
        $(byText("Create project")).click();
        return new ProjectPage(driver);
    }

    @Step("Удаление проекта с названием: '{projectName}'")
    public ProjectsListPage deleteProject(String projectName) {
        $(byText(projectName))
                .ancestor("tr")
                .find("button[aria-label='Open action menu']")
                .click();
        $("[data-testid=remove]").click();
        $x("//span[text()='Delete project']").click();
        return this;
    }
}