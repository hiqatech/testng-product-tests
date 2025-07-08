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

public class TestDeposit {

    @Test
    public void testLogin() {

        Hooks.setup("BankingWebDeposit", "LocalQAChrome");
        WebHelp.startMyWebDriver();

        LoginPage.loginWithUser("Rony Weasly");
        test.pass("Logged in with Ron Weasly user");

    }


    @Test
    public void testDeposit() {

        Hooks.setup("BankingWebDeposit","LocalQAChrome");
        WebHelp.startMyWebDriver();

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

        Hooks.tearDown();
    }

}