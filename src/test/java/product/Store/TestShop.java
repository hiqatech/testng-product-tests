package product.Store;

import static common.appium.AppHelp.*;

import common.appium.AppHelp;
import common.setup.Hooks;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import product.Store.pages.MainPage;

public class TestShop {

    @BeforeTest
    public void start(){
        Hooks.setup("StoreAppShop", "VirtualQA");
        AppHelp.startAppDriver("android","Pixel28");
    }
    @Test
    public void testShop() {
        waitSec(2000);
        selectDropDown(MainPage.countryDropDown,  "text(\"Switzerland\")");
        typeElement(MainPage.nameField, "Zoltan");
        setRadio(MainPage.radioMale);
        clickElement(MainPage.letsGoShop);
    }

    @AfterTest
    public void close(){
        AppHelp.stopAppDriver();
    }


}
