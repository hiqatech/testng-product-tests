package product.Banking.Tests;

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

import static common.setup.Hooks.html;
import static common.setup.Hooks.test;

/***
 * Tests Deposit feature
 */

public class TestDeposit {

    private static WebDriver driver = null;

    @BeforeTest
    public void setUp(){
        Hooks.setup("BankingWebDeposit","LocalQAChrome");
        driver = WebHelp.startMyWebDriver();
    }

    @Test
    public void testDeposit() throws IOException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithUser("Ron Weasly");
        test.pass("Logged in with Ron Weasly user");
        UserHomePage userHomePage = new UserHomePage(driver);
        userHomePage.goToDeposit();
        DepositPage depositPage = new DepositPage(driver);
        depositPage.makeDeposit("100");
        test.pass("Made 100 Deposit");
        depositPage.goToTransactions();
        TransactionsPage transactionsPage = new TransactionsPage(driver);
        transactionsPage.verifyTransaction("100");
        test.pass("Transaction 100 Confirmed");
        transactionsPage.selectReset();
        test.pass("Transactions Reset");
        transactionsPage.goToBack();
        depositPage = new DepositPage(driver);
        depositPage.verifyBalance("0");
        depositPage.logout();
        test.pass("Logged Out");

    }

    @AfterTest
    public void tearDown(){
        Hooks.tearDown();
    }

}