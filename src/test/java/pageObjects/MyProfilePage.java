package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyProfilePage extends BasePage {

    WebDriverWait wait;

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[normalize-space()='Log Out']") 
    WebElement logout;

    public void Logout() {

           
        logout.click();
    }
}