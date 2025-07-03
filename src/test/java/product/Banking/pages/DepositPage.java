package product.Banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static common.selenium.WebHelp.*;

public class DepositPage {
    protected WebDriver driver;

    private final By amount_field = By.xpath("//input[@placeholder='amount']");
    private final By deposit = By.xpath("//button[text()='Deposit']");
    private final By deposit_successful = By.xpath("//*[text()='Deposit Successful']");
    private final By account_number = By.xpath("//*[text()='Account Number : ']//following::strong[1]");
    private final By balance = By.xpath("//*[text()='Account Number : ']//following::strong[2]");
    private final By currency = By.xpath("//*[text()='Account Number : ']//following::strong[3]");

    private final By deposit_button = By.xpath("//button[contains(text(),'Deposit')]");
    private final By transactions_button = By.xpath("//button[contains(text(),'Transactions')]");
    private final By withdrawl_button = By.xpath("//button[contains(text(),'Withdrawl')]");
    private final By home_button = By.xpath("//button[text()='Home']");
    private final By logout_button = By.xpath("//button[text()='Logout']");

    public DepositPage(WebDriver driver){
        this.driver = driver;
    }

    public void makeDeposit(String amount){
        typeElement(amount_field,amount);
        clickElement(deposit);
        assertElementDisplayed(deposit_successful);
        assertElementText(balance,"100");
        sleep(2000);
    }

    public void verifyBalance(String amount){
        assertElementText(balance,amount);
    }

    public void goToTransactions() {
        clickElement(transactions_button);
    }

    public void logout() {
        clickElement(logout_button);
    }
}

