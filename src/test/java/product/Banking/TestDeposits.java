package product.Banking;

import common.selenium.WebHelp;
import common.setup.Hooks;
import org.testng.annotations.*;
import product.Banking.pages.DepositPage;
import product.Banking.pages.LoginPage;
import product.Banking.pages.TransactionsPage;
import product.Banking.pages.UserHomePage;
import static common.setup.Hooks.test;

 /*
 * Tests Deposit feature
 */

public class TestDeposits {

    @BeforeTest
    public void start(){
        Hooks.setup("BankingWebDeposit", "LocalQAChrome");
        WebHelp.startMyWebDriver();
    }

    /*
    @Test
    public void testLogin() {
        LoginPage.loginWithUser("Rony Weasly");
        test.pass("Logged in with Rony Weasly user");
    }
    */

    @Test
    public void testDeposit() {
        LoginPage.loginWithUser("Ron Weasly");
        UserHomePage.goToDeposit();
        DepositPage.makeDeposit("100");
        DepositPage.goToTransactions();
        TransactionsPage.verifyTransaction("100");
        TransactionsPage.selectReset();
        TransactionsPage.goToBack();
        DepositPage.verifyBalance("0");
        DepositPage.logout();
    }

    @AfterMethod
    public void finish(){
        Hooks.tearDown();
    }

}