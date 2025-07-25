package product.Banking.pages;

import common.setup.Hooks;
import org.openqa.selenium.By;
import static common.selenium.WebHelp.*;
import static common.setup.Hooks.AssertStep;
import static common.setup.Hooks.test;

public class LoginPage {

    private static final By home = By.xpath("");
    private static final By customer_login = By.xpath("//button[text()='Customer Login']");
    private static final By bank_manager_login = By.xpath("//button[text()='Bank Manager Login']");
    private static final By user_select = By.xpath("//select[@id='userSelect']");
    private static final By login_button = By.xpath("//button[text()='Login']");

    public static void loginWithUser(String userName) {
        gotoHomePage();
        customerLogin();
        userSelect(userName);
        clickLogin();
        test.pass("Logged in with "+ userName + " user");
    }

    public static void gotoHomePage(){
        AssertStep(navigateToUrl(System.getProperty("baseURL")));
    }

    public static void customerLogin(){
        AssertStep(clickElement(customer_login, "customer_login"));
    }

    public static void userSelect(String name){
        AssertStep(selectElementByText(user_select,"user_select", name));
    }

    public static void clickLogin(){
        AssertStep(clickElement(login_button,"login_button"));
    }


}
