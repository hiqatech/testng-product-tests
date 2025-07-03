package product.Banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static common.selenium.WebHelp.*;

public class LoginPage {
    protected WebDriver driver;

    private final By home = By.xpath("");
    private final By customer_login = By.xpath("//button[text()='Customer Login']");
    private final By bank_manager_login = By.xpath("//button[text()='Bank Manager Login']");
    private final By user_select = By.xpath("//select[@id='userSelect']");
    private final By login_button = By.xpath("//button[text()='Login']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void loginWithUser(String userName) {
        navigateToUrl(System.getProperty("baseURL"));
        clickElement(customer_login);
        selectElementByText(user_select,userName);
        clickElement(login_button);
    }
}
