package util;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import page.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class keyBoard extends BasePage {

    private static void touchKeyboard(int xPoint, int yPoint) {
        (new TouchAction(driver)).tap(PointOption.point(xPoint, yPoint)).perform();
    }

    public static void inputKeyValueForLeft(char keyValue) {
        switch (keyValue) {
            case '0':
                touchKeyboard(202, 664);
                break;
            case '1':
                touchKeyboard(153, 604);
                break;
            case '2':
                touchKeyboard(251, 604);
                break;
            case '3':
                touchKeyboard(352, 604);
                break;
            case '4':
                touchKeyboard(153, 552);
                break;
            case '5':
                touchKeyboard(251, 552);
                break;
            case '6':
                touchKeyboard(352, 552);
                break;
            case '7':
                touchKeyboard(153, 500);
                break;
            case '8':
                touchKeyboard(251, 500);
                break;
            case '9':
                touchKeyboard(352, 500);
                break;
            case '.':
                touchKeyboard(350, 664);

            case 'X':
                touchKeyboard(456, 500);
            case 'C':
                touchKeyboard(456, 552);
            case 'Y':
                touchKeyboard(456, 642);

            default:
                System.out.println("Input Invalue" + keyValue);
        }

    }

    public static void inputValueWithLeftKeyboard(String value){
        List<Character> valueL = value.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());
        valueL.forEach(keyBoard::inputKeyValueForLeft);
        inputKeyValueForLeft('Y');
    }

    public static void inputKeyValueForRight(char keyValue) {
        switch (keyValue) {
            case '0':
                touchKeyboard(1038, 664);
                break;
            case '1':
                touchKeyboard(996, 604);
                break;
            case '2':
                touchKeyboard(1093, 604);
                break;
            case '3':
                touchKeyboard(1194, 604);
                break;
            case '4':
                touchKeyboard(996, 552);
                break;
            case '5':
                touchKeyboard(1093, 552);
                break;
            case '6':
                touchKeyboard(1194, 552);
                break;
            case '7':
                touchKeyboard(986, 500);
                break;
            case '8':
                touchKeyboard(1093, 500);
                break;
            case '9':
                touchKeyboard(1194, 500);
                break;

            case '.':
                touchKeyboard(1188, 664);

            case 'X':
                touchKeyboard(1295, 500);
            case 'C':
                touchKeyboard(1295, 552);
            case 'Y':
                touchKeyboard(1292, 642);

            default:
                System.out.println("Input Invalid : " + keyValue);
        }

    }

    public static void inputValueWithRightKeyboard(String value){
        List<Character> valueL = value.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());
        valueL.forEach(keyBoard::inputKeyValueForRight);
        inputKeyValueForRight('Y');
    }
}
