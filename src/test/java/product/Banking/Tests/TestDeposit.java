package product.Banking.Tests;

import common.selenium.WebHelp;
import common.setup.Hooks;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import product.Banking.pages.LoginPage;
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

    }

    @AfterTest
    public void tearDown(){
        Hooks.tearDown();
    }

}