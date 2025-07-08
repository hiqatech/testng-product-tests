package product.Banking;

import common.selenium.WebHelp;
import common.setup.Hooks;
import org.testng.annotations.*;
import product.Banking.pages.DepositPage;
import product.Banking.pages.LoginPage;
import product.Banking.pages.TransactionsPage;
import product.Banking.pages.UserHomePage;
import static common.setup.Hooks.test;

/***
 * Tests Deposit feature
 */

public class TestDeposits {

    @BeforeTest
    public void start(){
        Hooks.setup("BankingWebDeposit", "LocalQAChrome");
        WebHelp.startMyWebDriver();
    }

    @Test
    public void testLogin() {
        LoginPage.loginWithUser("Rony Weasly");
        test.pass("Logged in with Rony Weasly user");
    }

    @Test
    public void testDeposit() {
        LoginPage.loginWithUser("Ron Weasly");
        test.pass("Logged in with Ron Weasly user");
        UserHomePage.goToDeposit();
        DepositPage.makeDeposit("100");
        test.pass("Made 100 Deposit");
        DepositPage.goToTransactions();
        TransactionsPage.verifyTransaction("100");
        test.pass("Transaction 100 Confirmed");
        TransactionsPage.selectReset();
        test.pass("Transactions Reset");
        TransactionsPage.goToBack();
        DepositPage.verifyBalance("0");
        DepositPage.logout();
        test.pass("Logged Out");
    }

    @AfterMethod
    public void finish(){
        Hooks.tearDown();
    }

}