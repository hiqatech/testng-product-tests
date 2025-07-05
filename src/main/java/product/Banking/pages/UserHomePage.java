package product.Banking.pages;

import org.openqa.selenium.By;
import static common.selenium.WebHelp.*;

public class UserHomePage {

    private static final By deposit_button = By.xpath("//button[contains(text(),'Deposit')]");
    private static final By transactions_button = By.xpath("//button[contains(text(),'Transactions')]");
    private static final By withdrawl_button = By.xpath("//button[contains(text(),'Withdrawl')]");
    private static final By home_button = By.xpath("//button[text()='Home']");
    private static final By logout_button = By.xpath("//button[text()='Logout']");

    private static final By account_number = By.xpath( "//*[text()='Account Number : ']//following::strong[1]");
    private static final By balance = By.xpath( "//*[text()='Account Number : ']//following::strong[2]");
    private static final By currency = By.xpath( "//*[text()='Account Number : ']//following::strong[3]");

    public static void goToDeposit() {
        clickElement(deposit_button);
    }
}
