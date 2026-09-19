package testBase;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginTest extends BaseClass {

    

    @Test(groups={"Sanity","Master"})
    public void Verify_login()
    {
    	
   logger.info("*********Starting testcases**********");

try {
        // Click My Account
        HomePage hp = new HomePage(driver);
        
        logger.info("*********Click on My Account**********");
        hp.clickMyAccount();
        
    

        // Login
        LoginPage lp = new LoginPage(driver);
        
        logger.info("*********Click on login**********");

        lp.Username(randomeString()+"@gmail.com");
        logger.info("*********Username testcases**********");
        lp.Pwd(randomeNumber()+"@123");
        logger.info("*********Password testcases**********");
        lp.ClickLogin();
        
        logger.info("*********End of Testcases**********");
    }
catch(Exception e) {
	logger.error("Test failed");
	logger.debug("Debugs logs");
	  
}
    }
}