package testBase;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.MyProfilePage;

public class MyProfileTest extends BaseClass {
	
	@Test
	public void verify_MyProfile() throws Exception {
		
		logger.info("Starting home test");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		
		
		logger.info("*********Click on login**********");
		
        LoginPage lp = new LoginPage(driver);
        lp.Username(p.getProperty("username"));
        lp.Pwd(p.getProperty("password"));
        lp.ClickLogin();
        
        logger.info("*********Click on Avtar **********");
        
        MyAccountPage macc = new MyAccountPage(driver);
		macc.ClickAvtar();
		
		logger.info("*********** Click on Logout ***********");

		MyProfilePage pp = new MyProfilePage(driver);

		logger.info("Logout action started");

		pp.Logout();

		logger.info("Logout action completed successfully");

		
	}
	
     
}
