package product.Store;

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
        AppHelp.startAppDriver("android","Pixel28", "General-store.apk");
    }
    @Test
    public void testShop() {
        MainPage.selectCountry("text(\"Switzerland\")");
        MainPage.typeName("Zoltan");
        MainPage.setSex("Male");
        MainPage.lestsGoShop();
    }

    @AfterTest
    public void close(){
        AppHelp.stopAppDriver();
    }


}
