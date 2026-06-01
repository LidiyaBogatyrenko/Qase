import io.qameta.allure.Description;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProjectTest extends BaseTest {

    @Test(testName = "Проверка создания проекта",
            priority = 1)
    @Description("Проверка создания проекта")
    public void checkCreateProject() {
        loginPage.login(user, password)
                .createNewProject("LA_TEST1", "LA_TEST1");
        String newProjectName = $("#project-name").getValue();
        projectsListPage.clickCreateProject()
                .getProjectName();
        projectPage.isPageLoaded(newProjectName);
        assertThat(projectPage.getProjectName()).isEqualToIgnoringCase(newProjectName + " repository");
    }

    @Test(testName = "Проверка удаления проекта",
            priority = 2)
    @Description("Проверка удаления проекта")
    public void deleteProject() {
        loginPage.login(user, password)
                .isPageOpened();
        projectsListPage.deleteProject("LA_TEST1");
    }

    @Test(testName = "Проверка создания и удаления нового проекта")
    @Description("Проверка создания и удаления нового проекта")
    public void checkCreateAndDeleteNewProject() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.login(user, password)
                .createNewProject("LA01", "LA01");
        String newProjectName = $("#project-name").getValue();
        projectsListPage.clickCreateProject();
        projectPage.getProjectName();
        projectPage.isPageLoaded(newProjectName);
        softAssert.assertEquals(projectPage.getProjectName(), newProjectName + " repository");
        open("/projects");
        $(byText("Projects")).click();
        projectsListPage.deleteProject(newProjectName);
        $x("//*[@id='application-content']//a[contains(text(), 'LA01')]").shouldNot(exist);
    }
}