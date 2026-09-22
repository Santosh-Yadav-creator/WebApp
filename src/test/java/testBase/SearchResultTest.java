package testBase;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchResultPage;

public class SearchResultTest extends BaseClass {

    @Test(groups = {"Sanity", "Master", "Regression"})
    public void Search_Result() {

        logger.info("*************** Starting Test Case ***************");

        // Create Home Page object
        HomePage hp = new HomePage(driver);

        logger.info("*************** Click on My Account ***************");
        hp.clickMyAccount();

        // Login
        logger.info("*************** Enter Login Details ***************");

        LoginPage lp = new LoginPage(driver);

        lp.Username(p.getProperty("username"));
        lp.Pwd(p.getProperty("password"));
        lp.ClickLogin();

        // My Account Page
        MyAccountPage macc = new MyAccountPage(driver);

        logger.info("*************** Click on Search ***************");
        macc.ClickSeach();

        logger.info("*************** Click on Search Bar ***************");
        macc.ClickonSeachBar();

        logger.info("*************** Enter Search Item ***************");
        macc.EnterSearchItem("Coffee");

        logger.info("*************** Click on Search Button ***************");
        macc.ClickSearchButton();

        // Search Result Page
        SearchResultPage srp = new SearchResultPage(driver);

        logger.info("*************** Verify Search Result Page ***************");

         srp.isTextDisplayed();

        
            logger.info("*************** Search Result Page Displayed ***************");
      
            logger.error("*************** Search Result Page NOT Displayed ***************");
        

        logger.info("*************** End of Test Case ***************");
    }
}