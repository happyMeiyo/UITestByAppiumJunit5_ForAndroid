package page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App extends BasePage{
    public static void start() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "7.1.2");
        desiredCapabilities.setCapability("deviceName", "DA08196340368");
        desiredCapabilities.setCapability("appPackage", "com.caibaopay.cashier");
        desiredCapabilities.setCapability("appActivity", ".view.login.LoginActivity");
        desiredCapabilities.setCapability("noReset", true);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        new WebDriverWait(driver, 30)
//                .until(x -> {
//                    System.out.println(System.currentTimeMillis());
//                    String xml=driver.getPageSource();
//                    Boolean exist=xml.contains("home_search") || xml.contains("image_cancel") ;
//                    System.out.println(exist);
//                    return exist;
//                });

    }

    public static void stop(){
        driver.quit();
    }

}
