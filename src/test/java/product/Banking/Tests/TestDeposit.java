package product.Banking.Tests;

import common.selenium.WebHelp;
import common.setup.Hooks;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import product.Banking.pages.DepositPage;
import product.Banking.pages.LoginPage;
import product.Banking.pages.TransactionsPage;
import product.Banking.pages.UserHomePage;

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
    public void testDeposit() {
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

    }

    @AfterTest
    public void tearDown(){
        Hooks.tearDown();
    }

}