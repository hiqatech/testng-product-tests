package common.appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

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
    public static AppiumDriverLocalService appiumService;
    public static AndroidDriver androidDriver;
    public static IOSDriver iosDriver;


    public static String startAppDriver(String platform) {
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
                options.setApp(System.getProperty("user.dir") + "\\src\\test\\resources\\ApiDemos.apk");

                androidDriver = new AndroidDriver(new URL(url), options);
                androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                androidDriver.quit();

            }
            else if(platform.equals("ios")) {
                iosDriver = new IOSDriver(new URL(url), options);
                iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                iosDriver.quit();
            }

            return  "PASS";

        }
        catch(Exception ex)
        {return  ex.toString();}
    }

    public static String stopAppDriver()
    {
        try
        {
            androidDriver.quit();
            iosDriver.quit();
            appiumService.stop();
            return  "PASS";
        }
        catch(Exception ex)
        {return "NOTE : " + ex.toString();}
    }

    public static String clickElementBy(String by, String locator)
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
            return  "PASS";
        }
        catch(Exception ex)
        {return "NOTE : " + ex.toString();}
    }

    public static String typeElementBy(String by,String locator,String text)
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
            return  "PASS";
        }
        catch(Exception ex)
        {return "NOTE : " + ex.toString();}
    }

}
