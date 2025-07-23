package product.Store;

import static common.appium.AppHelp.*;

import common.appium.AppHelp;
import common.setup.Hooks;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestShop {

    @BeforeTest
    public void start(){
        Hooks.setup("StoreAppShop", "VirtualQA");
        AppHelp.startAppDriver("android");
    }
    @Test
    public void testShop() {
        clickElementBy("AcID","");
        clickElementBy("XPath","");


    }


}
