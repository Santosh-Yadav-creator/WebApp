package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public SearchResultPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Search Result Page heading
    @FindBy(xpath = "//h2[contains(@class,'search-result')]")
    WebElement lnkResultPage;

    // Verify Search Result page is displayed
    public void isTextDisplayed() {

      
    }
}