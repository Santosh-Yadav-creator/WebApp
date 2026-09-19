package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage extends BasePage
{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}

	
	@FindBy(xpath="//span[@class='logo-container']")
	WebElement logo;
	
	@FindBy(xpath="//img[@alt='user avatar']")
	WebElement Avtar;

	

	public boolean isLogoDisplayed() {
	    return logo.isDisplayed();
	}
	
	public void ClickAvtar() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.elementToBeClickable(Avtar));

	    Avtar.click();
	}
}
