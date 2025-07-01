package product.Banking.pages;

import common.selenium.WebHelp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DepositPage {
    protected WebDriver driver;

    private By amount_field = By.xpath("//input[@placeholder='amount']");
    private By deposit = By.xpath("//button[text()='Deposit']");
    private By deposit_successful = By.xpath("//*[text()='Deposit Successful']");
    private By account_number = By.xpath("//*[text()='Account Number : ']//following::strong[1]");
    private By balance = By.xpath("//*[text()='Account Number : ']//following::strong[2]");
    private By currency = By.xpath("//*[text()='Account Number : ']//following::strong[3]");

    private By deposit_button = By.xpath("//button[contains(text(),'Deposit')]");
    private By transactions_button = By.xpath("//button[contains(text(),'Transactions')]");
    private By withdrawl_button = By.xpath("//button[contains(text(),'Withdrawl')]");
    private By home_button = By.xpath("//button[text()='Home']");
    private By logout_button = By.xpath("//button[text()='Logout']");

    public DepositPage(WebDriver driver){
        this.driver = driver;
    }

    public void makeDeposit(String amount){
        driver.findElement(amount_field).sendKeys(amount);
        driver.findElement(deposit).click();
        Assert.assertTrue(driver.findElement(deposit_successful).isDisplayed());
        Assert.assertTrue(driver.findElement(balance).getText().toString().equals(amount));
        WebHelp.takeScreenShot(driver);
    }

    public void verifyBalance(String amount){
        Assert.assertTrue(driver.findElement(balance).getText().toString().equals(amount));
        WebHelp.takeScreenShot(driver);
    }

    public void goToTransactions() {
        driver.findElement(transactions_button).click();
        WebHelp.sleep(1500);
    }

    public void logout() {
        driver.findElement(logout_button).click();
    }
}

