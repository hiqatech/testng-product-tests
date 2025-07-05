package common.setup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import common.selenium.WebHelp;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Hooks {

    public static ExtentHtmlReporter html;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void setup(String product,String environment)
    {
        html = new ExtentHtmlReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(html);
        test = extent.createTest(product,environment);
        html.config().setAutoCreateRelativePathMedia(true);

        System.setProperty("product",product);
        System.setProperty("environment",environment);
        System.setProperty("baseURL", AllURLs.getProductURL());

        LocalDateTime dateTime = LocalDateTime.now();

        System.setProperty("projectPath",System.getProperty("user.dir"));
        System.setProperty("systemTime", dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.setProperty("userID",System.getProperty("user.home").replace("C:\\Users\\",""));
        System.setProperty("downloadPath",System.getProperty("user.home")+"\\Downloads\\");
        System.setProperty("uploadPath",System.getProperty("user.home")+"\\Desktop\\");
        System.setProperty("reportPath",System.getProperty("projectPath")+"\\target\\surefire-reports\\");
        System.setProperty("filePath",System.getProperty("projectPath") + "\\src\\test\\resources\\files\\");
        System.setProperty("driverPath",System.getProperty("projectPath") + "\\src\\main\\resources\\webdrivers\\");

        print("************************************************************************************");

        print("SystemTime : " + System.getProperty("systemTime"));
        print("Product Tests Starts");
        print("Test : : " + System.getProperty("product"));
        print("ProjectPath : " + System.getProperty("projectPath"));
        print("FilePath : " + System.getProperty("filePath"));
        print("Product : " + System.getProperty("product"));
        print("Environment : " + System.getProperty("environment"));
        print("BaseURL : " + System.getProperty("baseURL"));
        print("************************************************************************************");

        try { File screenshots = new File(System.getProperty("filePath") + "\\screenshots\\");
            FileUtils.cleanDirectory(screenshots);}
        catch (Exception ex){}

    }



    public static void tearDown()
    {

        WebHelp.stopMyWebDriver();
        extent.flush();
        print("************************************************************************************");
        print("Product Tests Ends");
    }
    
    public static void print(String note){
        System.out.println(note);

    }

}
