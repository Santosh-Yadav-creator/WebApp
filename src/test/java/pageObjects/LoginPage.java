package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @FindBy(xpath = "//*[contains(@data-id,'inputUsername')]")
    WebElement loginUsername;

    @FindBy(xpath = "//*[contains(@data-id,'inputPassword')]")
    WebElement password;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    WebElement submit;

    public void Username(String Uname) {
        wait.until(ExpectedConditions.visibilityOf(loginUsername))
            .sendKeys(Uname);
    }

    public void Pwd(String Pname) {
        wait.until(ExpectedConditions.visibilityOf(password))
            .sendKeys(Pname);
    }

    public void ClickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(submit))
            .click();
    }
}