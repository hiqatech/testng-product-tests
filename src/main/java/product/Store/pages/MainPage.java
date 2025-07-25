package product.Store.pages;

import java.util.HashMap;

import static common.appium.AppHelp.*;
import static common.appium.AppHelp.tapButton;
import static common.appium.AppHelp.setRadio;
import static common.setup.Hooks.AssertStep;

public class MainPage {


    public static HashMap<String, String> elementLocators = new HashMap<String, String>();
    static {
        elementLocators.put("countryDropDown", "//android.widget.TextView" +
                "[@resource-id=\"android:id/text1\"]");
        elementLocators.put("nameField", "//android.widget.EditText" +
                "[@resource-id=\"com.androidsample.generalstore:id/nameField\"]");
        elementLocators.put("letsGoShop", "//android.widget.Button" +
                "[@resource-id=\"com.androidsample.generalstore:id/btnLetsShop\"]");
        elementLocators.put("radioMale", "com.androidsample.generalstore:id/radioMale");
    }

    public static String get(String element_name)
    {
        return elementLocators.get(element_name);
    }

    public static void register(){
        waitSec(2000);
        selectCountry("text(\"Switzerland\")");
        typeName("Zoltan");
        setSex("Male");
        lestsGoShop();
    }

    public static void selectCountry(String country){
        AssertStep(selectDropDown(get("countryDropDown"),"countryDropDown",  "text(\"Switzerland\")"));
    }

    public static void typeName(String name){
        AssertStep(typeElement(get("nameField"),"nameField", name));
    }

    public static void setSex(String sex){
        AssertStep(setRadio(get("radioMale"), "radioMale"));
    }

    public static void lestsGoShop(){
        AssertStep(tapButton(get("letsGoShop"),"letsGoShop"));
    }


}
