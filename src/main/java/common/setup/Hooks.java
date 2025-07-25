package common.setup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import common.appium.AppHelp;
import common.selenium.WebHelp;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.testng.AssertJUnit.fail;


public class Hooks {

    public static ExtentHtmlReporter html;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void testSetup(String product,String environment){
        AssertStep(setup(product,environment) +
                " : I setup the " + product + " product and " + environment + " environment");
    }

    public static String setup(String product,String environment)
    {
        html = new ExtentHtmlReporter("test-output//extent.html");
        extent = new ExtentReports();
        extent.attachReporter(html);
        test = extent.createTest(product,environment);
        html.config().setAutoCreateRelativePathMedia(true);

        System.setProperty("product",product);
        System.setProperty("environment",environment);
        System.setProperty("baseURL", AllURLs.getProductURL());

        LocalDateTime dateTime = LocalDateTime.now();

        System.setProperty("projectPath",System.getProperty("user.dir"));
        if(System.getProperty("projectPath").contains("testng-product-tests/testng-product-tests"))
            System.setProperty("projectPath",System.getProperty("user.dir")
                               .replace("testng-product-tests/testng-product-tests","testng-product-tests"));
        System.setProperty("systemTime", dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.setProperty("userID",System.getProperty("user.home").replace("C:\\Users\\",""));
        System.setProperty("downloadPath",System.getProperty("user.home")+"\\Downloads\\");
        System.setProperty("uploadPath",System.getProperty("user.home")+"\\Desktop\\");
        System.setProperty("reportPath",System.getProperty("projectPath")+"\\target\\surefire-reports\\");
        System.setProperty("extentPath",System.getProperty("projectPath")+"\\test-output\\");
        System.setProperty("filePath",System.getProperty("projectPath") + "\\src\\test\\resources\\files\\");
        System.setProperty("screenshotPath",System.getProperty("projectPath") + "\\test-output\\screenshots\\");
        System.setProperty("driverPath",System.getProperty("projectPath") + "\\src\\main\\resources\\webdrivers\\");
        System.setProperty("appDir",System.getProperty("projectPath") + "\\src\\main\\resources\\apps\\");

        print("************************************************************************************");
        print("Product Tests Starts");
        print("SystemTime : " + System.getProperty("systemTime"));
        print("Test : : " + System.getProperty("product"));
        print("ProjectPath : " + System.getProperty("projectPath"));
        print("ExtentPath : " + System.getProperty("extentPath"));
        print("Product : " + System.getProperty("product"));
        print("Environment : " + System.getProperty("environment"));
        print("BaseURL : " + System.getProperty("baseURL"));
        cleanReportFolder();
        return "PASS";

    }

    public static void tearDown()
    {
        if(System.getProperty("product").contains("Web"))
        WebHelp.stopWebDriver();
        AppHelp.stopAppDriver();
        extent.flush();
        print("Product Tests Ends");
        print("************************************************************************************");
    }

    public static void cleanReportFolder() {
        try {
            FileUtils.cleanDirectory(new File(System.getProperty("extentPath")));
        }catch (Exception ex){}
    }

    //-----------------------------------------------------------------------------//


    public static void AssertStep(String result)
    {
        if (!result.toUpperCase().contains("PASS")) {
            WebHelp.takeScreenShot();
            test.fail(getResultFailLog(result));
            print(result);
            fail();
        }
        else {
            test.pass(result.replace(",,,",""));
            print(result);
        }
    }

    public static void VerifyStep(String result)
    {
        if (!result.toUpperCase().contains("PASS")){
            WebHelp.takeScreenShot();
            test.info(getResultFailLog(result));
            //print(result);
        }
    }

    public static String getResultFailLog(String result){
        String extString = Arrays.asList(result.split(" ,,, " )).get(0);
        try{
            String desc = Arrays.asList(result.split(" ,,, " )).get(1);
            result = "FAIL " + desc + " caused by : " + extString;
        }
        catch (Exception ex) {
            return result;
        }

        return result;
    }

    public static void setProductEnv(String product,String environment){
        System.setProperty("product",product);
        System.setProperty("runEnvironment",environment);
        System.setProperty("baseURL", AllURLs.getProductURL());
        print("product : " + System.getProperty("product"));
        print("runEnvironment : " + System.getProperty("runEnvironment"));
        print("baseURL : " + System.getProperty("baseURL"));
    }

    public static void setProduct(String product){
        System.setProperty("product",product);
        System.setProperty("baseURL", AllURLs.getProductURL());
        print("product : " + System.getProperty("product"));
        print("baseURL : " + System.getProperty("baseURL"));
    }

    public static void setEnv(String environment){
        System.setProperty("runEnvironment",environment);
        print("runEnvironment : " + System.getProperty("runEnvironment"));
    }

    public static void setGrid(String grid){
        System.setProperty("runEnvironment",System.getProperty("runEnvironment") + grid);
        print("runEnvironment : " + System.getProperty("runEnvironment"));
    }

    public static void setBrowser(String browser){
        System.setProperty("System.setProperty",browser);
        print("browser : " + System.getProperty("browser"));
    }

    public static void print(String note){
        System.out.println(note);
    }

}
