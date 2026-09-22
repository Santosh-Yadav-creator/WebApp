package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage {

    WebDriver driver;
    WebDriverWait wait;

    public MyAccountPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//button[contains(@class,'search')]")
    WebElement search;

    @FindBy(xpath = "//input[@class='search-input']")
    WebElement searchBar;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;


    // Click Search icon
    public void ClickSeach() {

        wait.until(
                ExpectedConditions.elementToBeClickable(search)
        ).click();
    }


    // Click Search Bar
    public void ClickonSeachBar() {

        wait.until(
                ExpectedConditions.elementToBeClickable(searchBar)
        ).click();
    }


    // Enter search item
    public void EnterSearchItem(String item) {

        wait.until(
                ExpectedConditions.visibilityOf(searchBar)
        ).clear();

        searchBar.sendKeys(item);
    }


    // Click Search button
    public void ClickSearchButton() {

        wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        ).click();
    }


	public void isLogoDisplayed() {
		// TODO Auto-generated method stub
		
	}


	public void ClickAvtar() {
		// TODO Auto-generated method stub
		
	}
}