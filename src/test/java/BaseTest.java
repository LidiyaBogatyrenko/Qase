import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertyReader;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProjectsListPage projectsListPage;
    protected ProjectPage projectPage;

    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));

    @BeforeMethod
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.clickViaJs = true;
        Configuration.headless = false;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        Configuration.browserSize = null;
        Configuration.browserCapabilities = options;

        loginPage = new LoginPage(driver);
        projectsListPage = new ProjectsListPage(driver);
        projectPage = new ProjectPage(driver);

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterMethod
    public void quit() {
        getWebDriver().quit();
    }
}