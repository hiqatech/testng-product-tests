package product.Banking.pages;

import static common.selenium.WebHelp.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHomePage {

    protected WebDriver driver;

    private final By deposit_button = By.xpath("//button[contains(text(),'Deposit')]");
    private final By transactions_button = By.xpath("//button[contains(text(),'Transactions')]");
    private final By withdrawl_button = By.xpath("//button[contains(text(),'Withdrawl')]");
    private final By home_button = By.xpath("//button[text()='Home']");
    private final By logout_button = By.xpath("//button[text()='Logout']");

    private final By account_number = By.xpath( "//*[text()='Account Number : ']//following::strong[1]");
    private final By balance = By.xpath( "//*[text()='Account Number : ']//following::strong[2]");
    private final By currency = By.xpath( "//*[text()='Account Number : ']//following::strong[3]");

    public UserHomePage(WebDriver driver){
        this.driver = driver;
    }

    public void goToDeposit() {
        clickElement(deposit_button);
    }
}
