package page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage extends App {
    static MobileElement findElementById(String using) {
        return (MobileElement) driver.findElementById("com.caibaopay.cashier:id/" + using);
    }

    static MobileElement findElementByXpath(String using) {
        return (MobileElement) driver.findElementByXPath(using);
    }

    static List<MobileElement> findElementsByXpath(String using) {
        return driver.findElementsByXPath(using);
    }

    public static boolean waitForPresence(String by, int timeLimitInSeconds, String using){
        MobileElement mobileElement;
        try{
            switch (by) {
                case "By.Id":
                    mobileElement = findElementById(using);
                    break;
                case "By.Xpath":
                    mobileElement = findElementByXpath(using);
                    break;
                default:
                    System.out.println("查找类型错误");
                    return false;
            }
            WebDriverWait wait = new WebDriverWait(driver, timeLimitInSeconds);
            wait.until(ExpectedConditions.visibilityOf(mobileElement));
            return mobileElement.isDisplayed();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        } }

    static boolean isElementPresent(String by, String using) {
        return waitForPresence(by,2, using);
    }

    protected static void touchKeyboard(int xPoint, int yPoint) {
        (new TouchAction(driver)).tap(PointOption.point(xPoint, yPoint)).perform();
    }

    static void swipeByCoordinateWithElement(String element, double startX, double endX, double startY, double endY) {
        //获取屏幕大小
        Dimension screenSize = driver.manage().window().getSize();

        while (true) {
            try {
                (new TouchAction(driver))
                        .longPress(PointOption.point((int) (screenSize.width * startX), (int) (screenSize.height * startY)))
                        .moveTo(PointOption.point((int) (screenSize.width * endX), (int) (screenSize.height * endY)))
                        .release()
                        .perform();
            } catch (Exception e) {
                if (startX >= 1 || startX <= 0) {
                    System.out.println("超出屏幕边界，x起始坐标必须小于1且大于0");
                } else if (endX >= 1 || endX <= 0) {
                    System.out.println("超出屏幕边界，x终点坐标必须小于1且大于0");
                } else if (startY >= 1 || startY <= 0) {
                    System.out.println("超出屏幕边界，y起始坐标必须小于1且大于0");
                } else if (endY >= 1 || endY <= 0) {
                    System.out.println("超出屏幕边界，y终点坐标必须小于1且大于0");
                }
                return;
            }
            String pageSource = driver.getPageSource();
            //判断元素是否存在，存在则不等于-1,String.indexOf(xxxx)返回包含该字符串在父类字符串中起始位置，不包含则全部返回-1
            if (pageSource.contains(element)) {
                return;
            }
        }
    }

    /*
     * 注意事项
     * appium自带的输入法无法模拟控制键和基本键的，需要自行使用adb切换成搜狗或者android输入法，然后case完成之后记得切回appium输入法
     * 需要吊起输入法界面才可进行模拟发送基本键命令
     */
//    void InputKeyCode() {
//        try {
//
//            //Runtime.getRuntime().exec("adb shell ime set com.sohu.inputmethod.sogou/.SogouIME");
//            Process result = Runtime.getRuntime().exec("adb shell ime set com.sohu.inputmethod.sogou/.SogouIME");
//            System.out.println(result);
//
//            driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
//            driver.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
//
//            Runtime.getRuntime().exec("adb shell ime set com.netease.nie.yosemite/.ime.ImeService");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
