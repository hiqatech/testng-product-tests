package product.MainApp;

import static common.appium.AppHelp.*;

import common.appium.AppHelp;
import common.setup.Hooks;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWifi {

    @BeforeTest
    public void start(){
        Hooks.setup("MainAppWifi", "VirtualQA");
        AppHelp.startAppDriver("android");
    }
    @Test
    public void testWifi() {
        clickElementBy("AcID","Peformance");
        clickElementBy("XPath","//android.widget.TextView(@component-desc:'3, Performance Dependencies')");
        clickElementBy("ID","android:id/checkbox");
        clickElementBy("XPath","(//android.widget.RelativeLayout)[2]");
        typeElementBy("ID","android:id/edit","no1234");
        clickElementBy("ID","android.widget.button");

    }


}
