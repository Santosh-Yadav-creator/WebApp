package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.lang.RandomStringUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

    public Logger logger;

    protected static WebDriver driver;
    protected WebDriverWait wait;
    public Properties p;

    @BeforeClass(groups = {"sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(@Optional("windows") String os,
                      @Optional("chrome") String br) throws IOException {

        FileReader file = new FileReader("./src//test//resources//config.properties");

        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            // os
            if (os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN11);
            }
            else if (os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            }
            else
            {
                System.out.println("No matching os");
                return;
            }

            // browser
            switch (br.toLowerCase())
            {
            case "chrome":
                capabilities.setBrowserName("chrome");
                break;

            case "edge":
                capabilities.setBrowserName("MicrosoftEdge");
                break;

            case "firefox":
                capabilities.setBrowserName("firefox");
                break;

            default:
                System.out.println("No matching browser");
                return;
            }

            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444"),
                    capabilities
            );
        }

        if (p.getProperty("execution_env").equalsIgnoreCase("local"))
        {

            // Browser selection
            switch (br.toLowerCase())
            {

            case "chrome":
                driver = new ChromeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid browser: " + br
                );
            }
        }

        logger.info("Browser started: {}", br);

        // Maximize browser
        driver.manage().window().maximize();

        // Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open application
        driver.get(p.getProperty("appURL"));

        logger.info("Application opened successfully.");

        // Accept All Cookies
        try {

            WebElement acceptCookies = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("onetrust-accept-btn-handler")
                    )
            );

            acceptCookies.click();

            logger.info("Cookies accepted successfully.");

        }
        catch (Exception e) {

            logger.info("Accept All Cookies popup was not displayed.");
        }

        // Handle No Thanks popup inside Shadow DOM
        try {

            WebElement shadowHost = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("#mcforms-171596-204896")
                    )
            );

            SearchContext shadowRoot = shadowHost.getShadowRoot();

            WebElement noThanks = shadowRoot.findElement(
                    By.id("el_q2CQZe_1l")
            );

            noThanks.click();

            logger.info("No Thanks popup closed successfully.");

        }
        catch (Exception e) {

            logger.info("No Thanks popup was not displayed.");
        }
    }

    @AfterClass(groups = {"sanity", "Regression", "Master"})
    public void teardown() {

        if (driver != null) {

            logger.info("Closing browser.");

            driver.quit();
        }
    }

    // Generate random string
    public String randomeString() {

        return RandomStringUtils.randomAlphabetic(5);
    }

    // Generate random number
    public String randomeNumber() {

        return RandomStringUtils.randomNumeric(6);
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")
                + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}