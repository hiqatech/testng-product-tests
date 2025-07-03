package product.Banking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    protected WebDriver driver;

    private final By home = By.xpath("");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public HomePage makeDeposit(String userName, String password) {
        return new HomePage(driver);
    }
}

