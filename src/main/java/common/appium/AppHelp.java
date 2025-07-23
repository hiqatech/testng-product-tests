package common.appium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

import java.io.File;
import java.net.URL;
import java.time.Duration;

import static common.setup.Hooks.test;

public class AppHelp {

    public static String appData = System.getenv("APPDATA");
    public static String appiumJs = appData +
            "\\npm\\node_modules\\appium\\build\\lib\\main.js";
    public static String ipAddress = "127.0.0.1";
    public static String port = "4723";
    public static String url = "http://" + ipAddress + ":" + port + "/";
    public static String appName = "General-store.apk";
    public static String appPath = System.getProperty("appDir") + appName;
    public static AppiumDriverLocalService appiumService;
    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;


    public static void startAppDriver(String platform) {
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
                options.setDeviceName("Pixel_API_28");
                options.setApp(appPath);

                androidDriver = new AndroidDriver(new URL(url), options);
                androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                androidDriver.quit();

            }
            else if(platform.equals("ios")) {
                iosDriver = new IOSDriver(new URL(url), options);
                iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                iosDriver.quit();
            }
            waitSec(500);

        }
        catch(Exception ex)
        {
            failAppByEx(ex);}
    }

    public static void stopAppDriver()
    {
        try
        {
            androidDriver.quit();
            iosDriver.quit();
            appiumService.stop();
        }
        catch(Exception ex)
        {}
    }

    public static void installAppbyCommandLie()
    {
        try
        {
            //Start Emulator then
            //Android/Sdk/platform-tools adb install pathToApk
        }
        catch(Exception ex)
        {}
    }

    public static void clickElementBy(String by, String locator)
    {
        try
        {
            if(by.equals("AcID"))
                androidDriver.findElement(AppiumBy.accessibilityId(locator)).click();
            if(by.equals("ID"))
                androidDriver.findElement(AppiumBy.accessibilityId(locator)).click();
            if(by.equals("XPath"))
                androidDriver.findElement(AppiumBy.xpath(locator)).click();
            if(by.equals("Class"))
                androidDriver.findElements(AppiumBy.className(locator)).get(1).click();
            waitSec(500);
        }
        catch(Exception ex)
        {
            failAppByEx(ex);}
    }

    public static void typeElementBy(String by,String locator,String text)
    {
        try
        {
            if(by.equals("AcID"))
                androidDriver.findElement(AppiumBy.accessibilityId(locator)).sendKeys(text);
            if(by.equals("ID"))
                androidDriver.findElement(AppiumBy.accessibilityId(locator)).sendKeys(text);
            if(by.equals("XPath"))
                androidDriver.findElement(AppiumBy.xpath(locator)).sendKeys(text);
            if(by.equals("Class"))
                androidDriver.findElements(AppiumBy.className(locator)).get(1).sendKeys(text);
            waitSec(500);
        }
        catch(Exception ex)
        {failAppByEx(ex);}
    }

    public static void longClickElementBy(String by) {
        try {
            WebElement element = androidDriver.findElement(AppiumBy.xpath(""));
            ((JavascriptExecutor)androidDriver).executeScript("mobile: longClickGesture",
                    ImmutableMap.of("elementId",
                            ((RemoteWebElement)element).getId()),"duration",3000);
        } catch(Exception ex)
        {failAppByEx(ex);}
    }

    public static String getTextOfElementBy(String by) {
        try {
            return androidDriver.findElement(AppiumBy.xpath("")).getText();

        } catch(Exception ex)
        {failAppByEx(ex); return null;}
    }

    public static void dropDownElement(String locator, String value) {
        try {
            androidDriver.findElement(AppiumBy.xpath(locator)).click();
            androidDriver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(text("+locator+"));"));
            androidDriver.findElement(AppiumBy.xpath("android.widget.TextView[@text = "+value+"]")).click();
        } catch(Exception ex)
        {failAppByEx(ex);}
    }

    public static void radioElement(String locator) {
        try {
            androidDriver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radiofemale")).click();
        } catch(Exception ex)
        {failAppByEx(ex);}
    }

    public static void scrollToElement(String ele) {
        try {
            androidDriver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector()).scrollIntoView(text("+ele+"));"));
        } catch(Exception ex)
        {failAppByEx(ex);}
    }

    public static void swipeToElementBy(String by,String dir) {
        try {
            WebElement element = androidDriver.findElement(AppiumBy.xpath(""));
            ((JavascriptExecutor)androidDriver).executeScript("mobile: swipeGesture",
                    ImmutableMap.of("elementId",
                            ((RemoteWebElement)element).getId(),
                    "direction",dir,"percent",0.75));}
        catch(Exception ex)
        {failAppByEx(ex);}
    }

    public static void scrollToEnd() {
        try {
            boolean scrollMore;
            do{
                scrollMore = (Boolean) ((JavascriptExecutor) androidDriver).
                        executeScript("mobile: scrollGesture", ImmutableMap.of(
                                ));
                //"left", 100,"top", 100, "with", 200,"hight", 200, "direction", "dow","percent", 3.0
            }
            while (scrollMore);
        }
        catch(Exception ex)
        {failAppByEx(ex);}
    }



    public static void waitSec(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (Exception ex) {}
    }

    public static void failAppByEx(Exception ex){
        stopAppDriver();
        System.out.println(ex.toString());
        test.fail(ex.toString());
        Assert.assertTrue(false,ex.toString());
    }

}
