package product.Banking.pages;

import org.openqa.selenium.By;
import static common.selenium.WebHelp.*;
import static common.setup.Hooks.AssertStep;

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
        typeAmount(amount);
        clickDeposit();
        sleep(1000);
        verifySuccess();
        verifyBalance(amount);
        takeScreenShot();
    }

    public static void typeAmount(String amount){
        AssertStep(typeElement(amount_field,"amount_field",amount));
    }

    public static void clickDeposit(){
        AssertStep( clickElement(deposit,"deposit"));
    }

    public static void verifySuccess(){
        AssertStep( assertElementDisplayed(deposit_successful, "deposit_successful"));
    }

    public static void verifyBalance(String amount){
        AssertStep(assertElementText(balance,"balance",  amount));
        takeScreenShot();
    }

    public static void goToTransactions() {
        AssertStep(clickElement(transactions_button,"transactions_button"));
    }

    public static void logout() {
        AssertStep(clickElement(logout_button, "logout_button"));
    }
}

