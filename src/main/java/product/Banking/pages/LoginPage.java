package product.Banking.pages;

import org.openqa.selenium.By;
import static common.selenium.WebHelp.*;

public class LoginPage {

    private static final By home = By.xpath("");
    private static final By customer_login = By.xpath("//button[text()='Customer Login']");
    private static final By bank_manager_login = By.xpath("//button[text()='Bank Manager Login']");
    private static final By user_select = By.xpath("//select[@id='userSelect']");
    private static final By login_button = By.xpath("//button[text()='Login']");

    public static void loginWithUser(String userName) {
        navigateToUrl(System.getProperty("baseURL"));
        clickElement(customer_login);
        selectElementByText(user_select,userName);
        clickElement(login_button);
    }
}
