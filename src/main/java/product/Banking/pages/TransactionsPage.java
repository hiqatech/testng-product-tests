package product.Banking.pages;

import org.openqa.selenium.By;
import static common.selenium.WebHelp.*;
import static common.setup.Hooks.AssertStep;

public class TransactionsPage {
    
    private static final By transaction1_amount = By.xpath( "//table//tbody//tr[1]//td[2]");
    private static final By transaction2_amount = By.xpath( "//table//tbody//tr[2]//td[2]");
    private static final By transaction3_amount = By.xpath( "//table//tbody//tr[3]//td[2]");
    private static final By back_button = By.xpath( "//button[text()='Back']");
    private static final By reset_button = By.xpath( "//button[text()='Reset']");

    private static final By deposit_button = By.xpath("//button[contains(text(),'Deposit')]");
    private static final By transactions_button = By.xpath("//button[contains(text(),'Transactions')]");
    private static final By withdrawl_button = By.xpath("//button[contains(text(),'Withdrawl')]");
    private static final By home_button = By.xpath("//button[text()='Home']");
    private static final By logout_button = By.xpath("//button[text()='Logout']");
    
    public static void verifyTransaction(String amount) {
        AssertStep(assertElementText(transaction1_amount,"transaction1_amount", amount));
        takeScreenShot();
    }

    public static void selectReset() {
        AssertStep(clickElement(reset_button,"reset_button"));
    }

    public static void goToBack() {
        AssertStep(clickElement(back_button,"back_button"));
    }
}
