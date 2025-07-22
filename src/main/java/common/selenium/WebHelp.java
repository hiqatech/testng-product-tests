package common.selenium;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static common.setup.Hooks.print;
import static common.setup.Hooks.test;

public class WebHelp {

    public static WebDriver webDriver;
    public static int waitTimeMax= 5000;
    public static int waitTime = 200;
    public static int waitMax= 9;

    public static void startMyWebDriver() {
        try {
            DesiredCapabilities capabilities;
            if (System.getProperty("environment").contains("Chrome"))
                System.setProperty("driver", "Chrome");
            else if (System.getProperty("environment").contains("Edge"))
                System.setProperty("driver", "Edge");
            else if (System.getProperty("environment").contains("Firefox"))
                System.setProperty("driver", "Firefox");

            switch (System.getProperty("driver")) {

                case "Chrome":
                    String chromeDriverPath = System.getProperty("driverPath") + "chromedriver.exe";
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);

                    HashMap<String, Object> chromePrefs = new HashMap<>();
                    chromePrefs.put("download.prompt_for_download", false);
                    chromePrefs.put("download.default_directory", System.getProperty("downloadPath"));

                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", chromePrefs);
                    options.addArguments("--ssl-version-max=tls1");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--remote-allow-origins=*");
                    options.addArguments("--start-maximized");
                    options.addArguments("--screenshot");

                    capabilities = new DesiredCapabilities();

                    if (System.getProperty("environment").contains("Remote"))
                        webDriver = new RemoteWebDriver(new URL(System.getProperty("seleniumGrid")), capabilities);
                    else if (System.getProperty("environment").contains("Local"))
                        webDriver = new ChromeDriver(options);
                    else print("seleniumGrid" + " has not been defined.");

                    break;

                case "Edge":

                    String edgeDriverPath = System.getProperty("driverPath") + "msedgedriver.exe";
                    System.setProperty("webdriver.edge.driver", edgeDriverPath);

                    capabilities = new DesiredCapabilities();
                    capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

                    if (System.getProperty("environment").contains("Remote"))
                        webDriver = new RemoteWebDriver(new URL(System.getProperty("seleniumGrid")), capabilities);
                    else if (System.getProperty("environment").contains("Local"))
                        webDriver = new EdgeDriver();
                    else print(System.getProperty("runEnvironment") + " has not been defined yet.");

                    break;

                case "Firefox":

                    capabilities = new DesiredCapabilities();
                    String firefoxDriverPath = System.getProperty("driverPath") + "geckodriver.exe";
                    System.setProperty("webdriver.gecko.driver", firefoxDriverPath);

                    if (System.getProperty("runEnvironment").contains("Remote"))
                        webDriver = new RemoteWebDriver(new URL(System.getProperty("seleniumGrid")), capabilities);
                    else if (System.getProperty("environment").contains("Local"))
                        webDriver = new FirefoxDriver();
                    else print(System.getProperty("environment") + " has not been defined yet.");

                default:
                    print("webDriver " + System.getProperty("driver") + " has not been defined yet");

            }

            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(9));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
            webDriver.manage().window().maximize();


        } catch (Exception ex) {
            failWebByEx(ex);
        }
    }

    public static void stopMyWebDriver() {
        try {
            webDriver.quit();
        } catch (Exception ex) {}
    }

    public static void navigateToUrl(String url){
        try{
            webDriver.get(url); sleep(3000);
        } catch (Exception ex) {
            failWebByEx(ex);
        }
    }

    public static void sleep(int sleep) {
        try {
            webDriver.wait(sleep);
        } catch (Exception ex) {}
    }

    public static void takeScreenShot(){
        try{
        sleep(1000);
            TakesScreenshot scrShot =((TakesScreenshot)webDriver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            test.pass("", MediaEntityBuilder.createScreenCaptureFromPath(SrcFile.getPath()).build());
        } catch (Exception ex) {
            failWebByEx(ex);
        }
    }

    public static void clickElement(By elementBy){
        try{
            Assert.assertTrue(waitToAppear(elementBy));
            webDriver.findElement(elementBy).click();
        } catch (Exception ex) {
            failWebByEx(ex);
        }
    }

    public static void typeElement(By elementBy, String text){
        try{
            Assert.assertTrue(waitToAppear(elementBy));
            getWebElement(elementBy).sendKeys(text);
        sleep(500);
        } catch (Exception ex) {
            failWebByEx(ex);
        }
    }

    public static void assertElementDisplayed(By elementBy){
        try{
            Assert.assertTrue(waitToAppear(elementBy));
        } catch (Exception ex) {
            failWebByEx(ex);
        }
    }

    public static void assertElementNotDisplayed(By elementBy){
        try{
            Assert.assertTrue(waitToDisappear(elementBy));
        } catch (Exception ex) {
            failWebByEx(ex);
        }
    }

    public static void assertElementText(By elementBy, String text){
        try {
            Assert.assertTrue(waitToAppear(elementBy));
            Assert.assertEquals(getWebElement(elementBy).getText(),text);
        } catch (Exception ex) {
            failWebByEx(ex);
        }
    }

    public static void selectElementByText(By elementBy, String text){
        try{
            Assert.assertTrue(waitToAppear(elementBy));
            Select select = new Select(webDriver.findElement(elementBy));
            select.selectByVisibleText(text);WebHelp.sleep(200);
        } catch (Exception ex) {
            failWebByEx(ex);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void failWebByEx(Exception ex){
        takeScreenShot();
        stopMyWebDriver();
        test.fail(ex.toString());
        Assert.assertTrue(false,ex.toString());
    }

    public static String getTimeStamp(){
        String nano = String.valueOf(LocalDateTime.now().getNano());
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return  date + "-" + nano;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public static WebElement getWebElement(By elementBy)
    {
        return webDriver.findElement(elementBy);
    }

    public static Boolean isDisplayed(By elementBy)
    {
            WebElement webElement  = getWebElement(elementBy);
            if(webElement.isDisplayed() || webElement.isEnabled())
                return true;
            else return false;
    }

    public static boolean waitToAppear(By elementBy)
    {
        double startTime = 0;
        while (startTime < waitTimeMax)
        {
            sleep(waitTime);
            if(isDisplayed(elementBy))
                return true;
            else
            {
                sleep(waitTime);
                startTime = startTime + waitTime;
            }
        }
        return isDisplayed(elementBy);
    }

    public static boolean waitToDisappear(By elementBy)
    {
        double startTime = 0;
        while (startTime < waitTimeMax)
        {
            if(!isDisplayed(elementBy))
                return true;
            else
            {
                sleep(waitTime);
                startTime = startTime + waitTime;
            }
        }
        return false;
    }

}

