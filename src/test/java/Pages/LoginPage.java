package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;
    By userIdLabel = By.cssSelector("input[name=\"uid\"]");
    By passwordLabel = By.cssSelector("input[name=\"password\"]");
    By loginButton = By.cssSelector("input[name=\"btnLogin\"]");
    By resetButton = By.cssSelector("input[name=\"btnReset\"]");

    By generateAccessLink = By.cssSelector("body > div ol > li > a");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setup(String url) {
        this.driver.get(url);
    }

    public void clickLoginButton() {
        this.driver.findElement(loginButton).click();
    }

    public void addUserId(String userId) {
        this.driver.findElement(userIdLabel).sendKeys(userId);
    }

    public void addPassword(String password) {
        this.driver.findElement(passwordLabel).sendKeys(password);
    }
}
