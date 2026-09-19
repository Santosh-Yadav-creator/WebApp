package testBase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class MyAccountTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public  void verify_MyAccount() throws InterruptedException {
		
		logger.info("Starting MyAccount test");
		
		
	try {	
		
		HomePage hp=new HomePage(driver);
		
        logger.info("*********Click on My Account**********");

		
		hp.clickMyAccount();
		
		LoginPage lp=new LoginPage(driver);
		
		//Login
		
        logger.info("*********Click on login**********");
	
		
		lp.Username(p.getProperty("username"));
		lp.Pwd(p.getProperty("password"));
		lp.ClickLogin();
		
		//MyAccountPage
		
        logger.info("*********On AccountPage**********");

		
		MyAccountPage macc = new MyAccountPage(driver);
		macc.isLogoDisplayed();
		
		
	}
	catch(Exception e) {
		Assert.fail();
	}
		logger.info("Testcase completed");
		
		
	}

}
