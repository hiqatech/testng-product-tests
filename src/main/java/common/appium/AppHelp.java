package common.appium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class AppHelp {

    public static String appData = System.getenv("APPDATA");
    public static String appiumJs = appData +
            "\\npm\\node_modules\\appium\\build\\lib\\main.js";
    public static String ipAddress = "127.0.0.1";
    public static String port = "4723";
    public static String url = "http://" + ipAddress + ":" + port + "/";
    public static String appName = "General-store.apk";
    public static AppiumDriverLocalService appiumService;
    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;


    public static String startAppDriver(String platform, String device, String app) {
        try {

            appiumService = new AppiumServiceBuilder()
                    .withAppiumJS(new File(appiumJs))
                    .withIPAddress(ipAddress)
                    .usingPort(Integer.parseInt(port))
                    .withTimeout(Duration.ofSeconds(300))
                    .build();
            appiumService.start();

            platform = "android";
            UiAutomator2Options options = new UiAutomator2Options();

            if(platform.equals("android")) {
                options.setDeviceName(device);
                options.setApp(System.getProperty("appDir") + app);

                androidDriver = new AndroidDriver(new URL(url), options);
                androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            }
            else if(platform.equals("ios")) {
                iosDriver = new IOSDriver(new URL(url), options);
                iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }
            waitSec(500);

            return "PASS : I start the " + platform + " appDriver";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String stopAppDriver()
    {
        try
        {
            androidDriver.quit();
            iosDriver.quit();
            appiumService.stop();
            waitSec(500);
            return "PASS : I stop the appDriver";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String installAppByCommandLie(String app)
    {
        try
        {
            //Start Emulator then
            //Android/Sdk/platform-tools adb install pathToApk
            return "PASS : I install the " + app + " app";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String tapButton(By locator, String element)
    {
        try
        {
            //if(by.equals("XPath"))]]],
            androidDriver.findElement(locator).click();waitSec(1000);
            /*
            if(by.equals("AcID"))
                androidDriver.findElement(AppiumBy.accessibilityId(locator)).click();
            if(by.equals("ID"))
                androidDriver.findElement(AppiumBy.accessibilityId(locator)).click();
            if(by.equals("Class"))
                androidDriver.findElements(AppiumBy.className(locator)).get(1).click();
            */
            waitSec(500);
            return "PASS : I tap the " + element + " button ";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String typeElement(By locator,String element, String text)
    {
        try
        {
            androidDriver.findElement(locator).sendKeys(text);
            waitSec(1000);
            return "PASS : I type " + text + " into the the " + element;
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String longClickElementBy(By locator, String name) {
        try {
            WebElement element = androidDriver.findElement(locator);
            ((JavascriptExecutor)androidDriver).executeScript("mobile: longClickGesture",
                    ImmutableMap.of("elementId",
                            ((RemoteWebElement)element).getId()),"duration",3000);
            waitSec(500);
            return "PASS : I long tap the " + element + " button ";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String getTextOfElementBy(By locator, String element) {
        try {
            return androidDriver.findElement(AppiumBy.xpath("")).getText();
        } catch(Exception ex)
        {return ex.toString();}
    }

    public static String selectDropDown(By locator,String element, String value) {
        try {
            androidDriver.findElement(locator).click();waitSec(1000);
            androidDriver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(" + value + ");"));waitSec(1000);
                    value = value.replace("text(\"","").replace("\")","");
            androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@text = '"+value+"']")).click();
            waitSec(1000);
            return "PASS : I select " + value + " from the " + element;
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String setRadio(By locator, String element) {
        try {
            androidDriver.findElement(locator).click();
            waitSec(500);
            return "PASS  : I set the " + element + " radio";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String scrollToElement(AppiumBy locator, String element) {
        try {
            androidDriver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView("+locator+");"));
            waitSec(500);
            return "PASS  : I scroll to the " + element + " radio";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String swipeToElementBy(AppiumBy locator,String dir) {
        try {
            WebElement element = androidDriver.findElement(AppiumBy.xpath(""));
            ((JavascriptExecutor)androidDriver).executeScript("mobile: swipeGesture",
                    ImmutableMap.of("elementId",
                            ((RemoteWebElement)element).getId(),
                    "direction",dir,"percent",0.75));
            waitSec(500);
            return "PASS  : I swipe to the " + element + " element";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static String scrollToEnd() {
        try {
            boolean scrollMore;
            do{
                scrollMore = (Boolean) ((JavascriptExecutor) androidDriver).
                        executeScript("mobile: scrollGesture", ImmutableMap.of(
                                ));
                //"left", 100,"top", 100, "with", 200,"hight", 200, "direction", "dow","percent", 3.0

            }
            while (scrollMore);
            waitSec(500);
            return "PASS  : I scroll to the end";
        }
        catch(Exception ex)
        { return ex.toString();}
    }

    public static void waitSec(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (Exception ex) {}
    }

}
