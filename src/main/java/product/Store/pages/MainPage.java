package product.Store.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import static common.appium.AppHelp.*;
import static common.appium.AppHelp.tapButton;
import static common.appium.AppHelp.setRadio;
import static common.setup.Hooks.AssertStep;

public class MainPage {
    
    private static final By countryDropDown = AppiumBy.xpath("//android.widget.TextView" +
                "[@resource-id=\"android:id/text1\"]");
    private static final By nameField = AppiumBy.xpath("//android.widget.EditText" +
            "[@resource-id=\"com.androidsample.generalstore:id/nameField\"]");
    private static final By letsGoShop = AppiumBy.xpath("//android.widget.Button" +
            "[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]");
    private static final By radioMale = (AppiumBy) AppiumBy.id("com.androidsample.generalstore:id/radioMale");

    public static void register(){
        waitSec(2000);
        selectCountry("text(\"Switzerland\")");
        typeName("Zoltan");
        setSex("Male");
        lestsGoShop();
    }

    public static void selectCountry(String country){
        AssertStep(selectDropDown(countryDropDown,"countryDropDown",  "text(\"Switzerland\")"));
    }

    public static void typeName(String name){
        AssertStep(typeElement(nameField,"nameField", name));
    }

    public static void setSex(String sex){
        AssertStep(setRadio(radioMale, "radioMale"));
    }

    public static void lestsGoShop(){
        AssertStep(tapButton(letsGoShop,"letsGoShop"));
    }


}
