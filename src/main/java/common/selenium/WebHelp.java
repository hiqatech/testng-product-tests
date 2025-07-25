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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static common.setup.Hooks.print;
import static common.setup.Hooks.test;

public class WebHelp {

    public static WebDriver webDriver;
    public static int waitTimeMax= 5000;
    public static int waitTime = 200;

    public static String startWebDriver() {
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

            return "PASS : I start the " + System.getProperty("driver") + " webDriver";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String stopWebDriver() {
        try {
            webDriver.quit();
            return "PASS : I stop the " + System.getProperty("driver") + " webDriver";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String navigateToUrl(String url){
        try{
            webDriver.get(url); sleep(3000);
            return "PASS : I navigate to the " + url + " url";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String takeScreenShot(){
        try{
        sleep(1000);
            TakesScreenshot scrShot =((TakesScreenshot)webDriver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            test.pass("", MediaEntityBuilder.createScreenCaptureFromPath(SrcFile.getPath()).build());
            return "PASS : I take screenshot";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String clickElement(By locator, String element){
        try{
            getWebElement(locator).click();
            return "PASS : I click the " + element + " element";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String typeElement(By locator, String element, String text){
        try{
            getWebElement(locator).sendKeys(text);
            return "PASS : I type " + text + " into the " + element;
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String assertElementDisplayed(By locator, String element){
        try{
            Assert.assertTrue(waitToAppear(locator).isDisplayed());
            return "PASS : I assert " + element + " displayed";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String assertElementNotDisplayed(By locator, String element){
        try{
            Assert.assertTrue(!waitToDisappear(locator).isDisplayed());
            return "PASS : I assert " + element + " not displayed";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String assertElementText(By locator,String element, String text){
        try {
            Assert.assertEquals(getWebElement(locator).getText(),text);
            return "PASS : I assert " + element + " has text " + text;
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String selectElementByText(By locator, String element, String text){
        try{
            Select select = new Select(getWebElement(locator));
            select.selectByVisibleText(text);WebHelp.sleep(200);
            return "PASS : I select " + text + " from the " + element;
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public static void sleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (Exception ex) {}
    }

    public static WebElement getWebElement(By locator)
    {
        return waitToAppear(locator);
    }

    public static Boolean isDisplayed(By locator)
    {
            WebElement webElement  = webDriver.findElement(locator);
            if(webElement.isDisplayed() || webElement.isEnabled())
                return true;
            else return false;
    }

    public static WebElement waitToAppear(By locator)
    {
        double startTime = 0;
        while (startTime < waitTimeMax)
        {
            sleep(waitTime);
            if(isDisplayed(locator))
                return webDriver.findElement(locator);
            else
            {
                sleep(waitTime);
                startTime = startTime + waitTime;
            }
        }
        return null;
    }

    public static WebElement waitToDisappear(By locator)
    {
        double startTime = 0;
        while (startTime < waitTimeMax)
        {
            if(!isDisplayed(locator))
                return getWebElement(locator);
            else
            {
                sleep(waitTime);
                startTime = startTime + waitTime;
            }
        }
        return null;
    }

}

