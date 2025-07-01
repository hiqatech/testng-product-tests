package product.Banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
    protected WebDriver driver;

    private By home = By.xpath("");
    private By customer_login = By.xpath("//button[text()='Customer Login']");
    private By bank_manager_login = By.xpath("//button[text()='Bank Manager Login']");
    private By user_select = By.xpath("//select[@id='userSelect']");
    private By login_button = By.xpath("//button[text()='Login']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void loginWithUser(String userName) {
        driver.get(System.getProperty("baseURL"));
        driver.findElement(customer_login).click();
        Select select = new Select(driver.findElement(user_select));
        select.selectByVisibleText(userName);
        driver.findElement(login_button).click();
    }
}
