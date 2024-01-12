import Pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Login {
    private final String baseUrl = "https://demo.guru99.com/V1/index.php";
    private final String drivePath= "src/test/resources/chromedriver";
    private WebDriver driver;

    private LoginPage loginPage;

    @BeforeAll
    public void setup() {
        System.setProperty("webdriver.chrome.driver", drivePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        loginPage.setup(baseUrl);
    }

    @AfterAll
    public void tearDown() {
        driver.close();
    }

    @Test
    @DisplayName("When click on Login button without USERID and Password, Then the application must show an error message")
    public void whenClickLoginWithoutCredentials_ThenApplicationShowsAnErrorMessage() {
        String errorMessageReceived = "";

        loginPage.clickLoginButton();
        errorMessageReceived = driver.switchTo().alert().getText();

        checkMessageError(errorMessageReceived);
    }

    @Test
    @DisplayName("When click on Login button without USERID, Then the application must show an error message")
    public void whenClickLoginWithoutUserId_ThenApplicationShowsAnErrorMessage() throws Exception {
        addOnlyOneCredentialAndTryLogin("password", "12345");
    }

    @Test
    @DisplayName("When click on Login button without Password, Then the application must show an error message")
    public void whenClickLoginWithoutPassword_ThenApplicationShowsAnErrorMessage() throws Exception {
        addOnlyOneCredentialAndTryLogin("user", "bruno");
    }

    public void addOnlyOneCredentialAndTryLogin(String credentialType, String value) throws Exception {
        String errorMessageReceived = "";

        if (credentialType.toLowerCase().equals("user") || credentialType.toLowerCase().equals("password")) {
            if (credentialType.toLowerCase().equals("user")) {
                loginPage.addUserId(value);
            } else {
                loginPage.addPassword(value);
            }
        } else {
            throw new Exception("Only 'user' or 'password' is accepted as a credentialType parameter");
        }

        loginPage.clickLoginButton();
        errorMessageReceived = driver.switchTo().alert().getText();

        checkMessageError(errorMessageReceived);
    }

    public void checkMessageError(String errorMessageReceived) {
        String expectedErrorMessage = "User is not valid";

        assertEquals(errorMessageReceived, expectedErrorMessage);
        driver.switchTo().alert().accept();
    }
}
