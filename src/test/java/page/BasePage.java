package page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.util.List;

public class BasePage {
    protected static AndroidDriver driver;

    static MobileElement findElementById(String using) {
        return (MobileElement) driver.findElementById("com.caibaopay.cashier:id/" + using);
    }

    static MobileElement findElementByXpath(String using) {
        return (MobileElement) driver.findElementByXPath(using);
    }

    static List<MobileElement> findElementsByXpath(String using) {
        return (List<MobileElement>) driver.findElementsByXPath(using);
    }


    static boolean isElementPresent(String by, String using) {
        try {
            switch (by) {
                case "By.Id":
                    findElementById(using);
                    break;
                case "By.Xpath":
                    findElementByXpath(using);
                    break;
                default:
                    System.out.println("查找类型错误");
                    return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
