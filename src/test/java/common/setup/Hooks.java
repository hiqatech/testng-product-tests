package common.setup;

import common.selenium.WebHelp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    //------------------------------------------------------------------------//

    public static void setup(String product,String environment)
    {
        System.setProperty("product",product);
        System.setProperty("environment",environment);
        System.setProperty("baseURL", AllURLs.getProductURL());

        LocalDateTime dateTime = LocalDateTime.now();

        System.setProperty("projectPath",System.getProperty("user.dir"));
        System.setProperty("systemTime", dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.setProperty("userID",System.getProperty("user.home").replace("C:\\Users\\",""));
        System.setProperty("downloadPath",System.getProperty("user.home")+"\\Downloads\\");
        System.setProperty("uploadPath",System.getProperty("user.home")+"\\Desktop\\");
        System.setProperty("reportPath",System.getProperty("projectPath")+"\\target\\reports\\");
        System.setProperty("filePath",System.getProperty("projectPath") + "\\src\\test\\resources\\files\\");
        System.setProperty("driverPath",System.getProperty("projectPath") + "\\src\\test\\resources\\webdrivers\\");

        System.out.println("************************************************************************************");

        System.out.println("SystemTime : " + System.getProperty("systemTime"));
        System.out.println("Product Tests Starts");
        System.out.println("Test : " );
        System.out.println("ProjectPath : " + System.getProperty("projectPath"));
        System.out.println("Environment : " + System.getProperty("environment"));
        System.out.println("BaseURL : " + System.getProperty("baseURL"));
        System.out.println("************************************************************************************");

    }

    public static void tearDown()
    {
        WebHelp.stopMyWebDriver();
        System.out.println("************************************************************************************");
    }

    //-----------------------------------------------------------------------------//

}
