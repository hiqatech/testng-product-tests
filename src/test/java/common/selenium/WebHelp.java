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
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import static common.setup.Hooks.test;

public class WebHelp {

    public static WebDriver webDriver;

    public static WebDriver startMyWebDriver() {
        try {
            DesiredCapabilities capabilities;
            if (System.getProperty("environment").contains("Chrome"))
                System.setProperty("driver", "Chrome");
            else if (System.getProperty("environment").contains("Edge"))
                System.setProperty("driver", "Edge");
            else if (System.getProperty("environment").contains("Firefox"))
                System.setProperty("driver", "Firefox");

            String driver = System.getProperty("driver");
            String driverPath = System.getProperty("driverPath");

            switch (driver) {

                case "Chrome":
                    String chromeDriverPath = driverPath + "chromedriver.exe";
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);

                    HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                    chromePrefs.put("download.prompt_for_download", false);
                    chromePrefs.put("download.default_directory", System.getProperty("downloadPath"));

                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", chromePrefs);
                    options.addArguments("--ssl-version-max=tls1");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--disable-extensions");
                    options.addArguments("--start-maximized");
                    options.addArguments("--screenshot");

                    capabilities = new DesiredCapabilities();

                    if (System.getProperty("environment").contains("Remote"))
                        webDriver = new RemoteWebDriver(new URL(System.getProperty("seleniumGrid")), capabilities);
                    else if (System.getProperty("environment").contains("Local"))
                        webDriver = new ChromeDriver(options);
                    else System.out.println("seleniumGrid" + " has not been defined.");

                    break;

                case "Edge":

                    String edgeDriverPath = driverPath + "msedgedriver.exe";
                    System.setProperty("webdriver.edge.driver", edgeDriverPath);

                    capabilities = new DesiredCapabilities();
                    capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

                    if (System.getProperty("environment").contains("Remote"))
                        webDriver = new RemoteWebDriver(new URL(System.getProperty("seleniumGrid")), capabilities);
                    else if (System.getProperty("environment").contains("Local"))
                        webDriver = new EdgeDriver();
                    else System.out.println(System.getProperty("runEnvironment") + " has not been defined yet.");

                    break;

                case "Firefox":

                    capabilities = new DesiredCapabilities();
                    String firefoxDriverPath = driverPath + "geckodriver.exe";
                    System.setProperty("webdriver.gecko.driver", firefoxDriverPath);

                    if (System.getProperty("runEnvironment").contains("Remote"))
                        webDriver = new RemoteWebDriver(new URL(System.getProperty("seleniumGrid")), capabilities);
                    else if (System.getProperty("environment").contains("Local"))
                        webDriver = new FirefoxDriver();
                    else System.out.println(System.getProperty("environment") + " has not been defined yet.");

                default:
                    System.out.println("webDriver " + driver + " has not been defined yet");

            }

            webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(9));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
            webDriver.manage().window().maximize();

            return webDriver;

        } catch (Exception ex) {
            System.out.println(ex);
            return webDriver;
        }
    }


    public static void stopMyWebDriver() {
        try {
            webDriver.close();
            webDriver.quit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void sleep(int sleep) {
        try {
            webDriver.wait(sleep);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void takeScreenShot(WebDriver webdriver, String screen){
        sleep(1000);
        try {
            TakesScreenshot scrShot =((TakesScreenshot)webdriver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            String filePath = System.getProperty("filePath") + "\\screenshots\\" + getTimeStamp() + ".jpg";
            File DestFile=new File(filePath);
            FileUtils.copyFile(SrcFile, DestFile);
            String path = "<img src=\"file://\"" + filePath + "\" alt=\"\"/>";
            Reporter.log(path);
            sleep(1000);

            test.pass(screen, MediaEntityBuilder.createScreenCaptureFromPath(filePath).build());
            test.addScreenCaptureFromPath(filePath);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static String getTimeStamp(){
        String nano = String.valueOf(LocalDateTime.now().getNano());
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return  date + "-" + nano;
    }

    public static void clickElement(By elementBy){
        WebElement element = (new WebDriverWait(webDriver, Duration.ofSeconds(8)))
                .until(ExpectedConditions.elementToBeClickable(elementBy));
        webDriver.findElement(elementBy).click();
        sleep(500);
    }

    public static void typeElement(By elementBy, String text){
        WebElement element = (new WebDriverWait(webDriver, Duration.ofSeconds(8)))
                .until(ExpectedConditions.visibilityOfElementLocated(elementBy));
        webDriver.findElement(elementBy).sendKeys(text);
        sleep(500);
    }

    public static void assertElementDisplayed(By elementBy){
        Assert.assertTrue(webDriver.findElement(elementBy).isDisplayed());
        takeScreenShot(webDriver, elementBy.toString());
    }

    public static void assertElementText(By elementBy, String text){
        Assert.assertEquals(webDriver.findElement(elementBy).getText(),text);
        takeScreenShot(webDriver, elementBy.toString());
    }

    public static void selectElementByText(By elementBy, String text){
        Select select = new Select(webDriver.findElement(elementBy));
        select.selectByVisibleText(text);WebHelp.sleep(200);
    }

    public static void navigateToUrl(String url){
        webDriver.get(url); sleep(3000);
    }


}

