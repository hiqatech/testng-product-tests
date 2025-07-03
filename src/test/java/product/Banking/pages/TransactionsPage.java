package product.Banking.pages;

import common.selenium.WebHelp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static common.selenium.WebHelp.assertElementText;
import static common.selenium.WebHelp.clickElement;

public class TransactionsPage {
    protected WebDriver driver;

    private By transaction1_amount = By.xpath( "//table//tbody//tr[1]//td[2]");
    private By transaction2_amount = By.xpath( "//table//tbody//tr[2]//td[2]");
    private By transaction3_amount = By.xpath( "//table//tbody//tr[3]//td[2]");
    private By back_button = By.xpath( "//button[text()='Back']");
    private By reset_button = By.xpath( "//button[text()='Reset']");

    private By deposit_button = By.xpath("//button[contains(text(),'Deposit')]");
    private By transactions_button = By.xpath("//button[contains(text(),'Transactions')]");
    private By withdrawl_button = By.xpath("//button[contains(text(),'Withdrawl')]");
    private By home_button = By.xpath("//button[text()='Home']");
    private By logout_button = By.xpath("//button[text()='Logout']");

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
