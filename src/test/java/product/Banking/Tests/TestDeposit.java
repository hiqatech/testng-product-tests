package product.Banking.Tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import common.selenium.WebHelp;
import common.setup.Hooks;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import product.Banking.pages.DepositPage;
import product.Banking.pages.LoginPage;
import product.Banking.pages.TransactionsPage;
import product.Banking.pages.UserHomePage;

import java.io.IOException;

/***
 * Tests Deposit feature
 */

public class TestDeposit {

    private static WebDriver driver = null;

    @BeforeTest
    public void setUp(){
        Hooks.setup("BankingWeb","LocalQAChrome");
        driver = WebHelp.startMyWebDriver();
    }

    @Test
    public void testDeposit() throws IOException {
        ExtentTest test = Hooks.extent.createTest("testDeposit", "description");
        test.log(Status.INFO, "This step shows usage of log(status, details)");
        test.info("This step shows usage of info(details)");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithUser("Ron Weasly");
        UserHomePage userHomePage = new UserHomePage(driver);
        userHomePage.goToDeposit();
        DepositPage depositPage = new DepositPage(driver);
        depositPage.makeDeposit("100");
        depositPage.goToTransactions();
        TransactionsPage transactionsPage = new TransactionsPage(driver);
        transactionsPage.verifyTransaction("100");
        transactionsPage.selectReset();
        transactionsPage.goToBack();
        depositPage = new DepositPage(driver);
        depositPage.verifyBalance("0");
        depositPage.logout();

        String screenshotPath = System.getProperty("filePath")+ "\\screenshots\\transaction.png";
        test.pass("ScreenShot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        test.addScreenCaptureFromPath(screenshotPath);

    }

    @AfterTest
    public void tearDown(){
        Hooks.tearDown();
    }

}