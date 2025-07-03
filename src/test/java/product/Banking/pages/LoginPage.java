package product.Banking.pages;

import common.selenium.WebHelp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        WebHelp.navigateToUrl(System.getProperty("baseURL"));
        WebHelp.clickElement(customer_login);
        WebHelp.selectElementByText(user_select,userName);
        WebHelp.clickElement(login_button);
    }
}
