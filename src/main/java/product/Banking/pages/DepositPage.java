package product.Banking.pages;

import org.openqa.selenium.By;
import static common.selenium.WebHelp.*;

public class DepositPage {
   
    private static final By amount_field = By.xpath("//input[@placeholder='amount']");
    private static final By deposit = By.xpath("//button[text()='Deposit']");
    private static final By deposit_successful = By.xpath("//*[text()='Deposit Successful']");
    private static final By account_number = By.xpath("//*[text()='Account Number : ']//following::strong[1]");
    private static final By balance = By.xpath("//*[text()='Account Number : ']//following::strong[2]");
    private static final By currency = By.xpath("//*[text()='Account Number : ']//following::strong[3]");

    private static final By deposit_button = By.xpath("//button[contains(text(),'Deposit')]");
    private static final By transactions_button = By.xpath("//button[contains(text(),'Transactions')]");
    private static final By withdrawl_button = By.xpath("//button[contains(text(),'Withdrawl')]");
    private static final By home_button = By.xpath("//button[text()='Home']");
    private static final By logout_button = By.xpath("//button[text()='Logout']");

    public static void makeDeposit(String amount){
        typeElement(amount_field,amount);
        clickElement(deposit);
        assertElementDisplayed(deposit_successful);
        assertElementText(balance,"100");
        takeScreenShot("deposit_successful");
        sleep(2000);
    }

    public static void verifyBalance(String amount){
        assertElementText(balance,amount);
        takeScreenShot("balance");
    }

    public static void goToTransactions() {
        clickElement(transactions_button);
    }

    public static void logout() {
        clickElement(logout_button);
    }
}

