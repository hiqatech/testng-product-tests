package product.Banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static common.selenium.WebHelp.*;

public class TransactionsPage {
    protected WebDriver driver;

    private final By transaction1_amount = By.xpath( "//table//tbody//tr[1]//td[2]");
    private final By transaction2_amount = By.xpath( "//table//tbody//tr[2]//td[2]");
    private final By transaction3_amount = By.xpath( "//table//tbody//tr[3]//td[2]");
    private final By back_button = By.xpath( "//button[text()='Back']");
    private final By reset_button = By.xpath( "//button[text()='Reset']");

    private final By deposit_button = By.xpath("//button[contains(text(),'Deposit')]");
    private final By transactions_button = By.xpath("//button[contains(text(),'Transactions')]");
    private final By withdrawl_button = By.xpath("//button[contains(text(),'Withdrawl')]");
    private final By home_button = By.xpath("//button[text()='Home']");
    private final By logout_button = By.xpath("//button[text()='Logout']");

    public TransactionsPage(WebDriver driver){
        this.driver = driver;
    }

    public void verifyTransaction(String amount) {
        assertElementText(transaction1_amount,amount);
    }

    public void selectReset() {
        clickElement(reset_button);
    }

    public void goToBack() {
        clickElement(back_button);
    }
}
