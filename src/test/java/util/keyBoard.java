package util;

import page.BasePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class keyBoard extends BasePage {
    private static Map<Object, KeyBoardLayout> keyBoards = new HashMap<>();
    private static final String LEFT = "LEFT";
    private static final String RIGHT= "RIGHT";
    private static final String MIDDLE = "MIDDLE";
    private static final String TEMP = "TEMP";

    static{
        KeyBoardLayout keyBoardLayout1 = new KeyBoardLayout();
        keyBoardLayout1.setLeft(100).setTop(470);
        keyBoards.put(LEFT, keyBoardLayout1);

        KeyBoardLayout keyBoardLayout2 = new KeyBoardLayout();
        keyBoardLayout2.setLeft(943).setTop(470);
        keyBoards.put(RIGHT, keyBoardLayout2);

        KeyBoardLayout keyBoardLayout3 = new KeyBoardLayout();
        keyBoardLayout3.setLeft(523).setTop(470);
        keyBoards.put(MIDDLE, keyBoardLayout3);

        KeyBoardLayout keyBoardLayout4 = new KeyBoardLayout();
        keyBoardLayout4.setLeft(680).setTop(250);
        keyBoards.put(TEMP, keyBoardLayout4);
    }

    private static void inputKeyValue(String keyboardName, char keyValue){
        int x = keyBoards.get(keyboardName).getX_Coordinate(keyValue);
        int y = keyBoards.get(keyboardName).getY_Coordinate(keyValue);
        touchKeyboard(x, y);
    }

    public static void inputValueWithKeyboard(String keyboardName, String value) {
        List<Character> valueL = value.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());

        for (Character keyValue : valueL) {
            inputKeyValue(keyboardName, keyValue);
        }
    }


    public static void inputValueWithLeftKeyboard(String value) {
        inputValueWithKeyboard(LEFT, value);
        inputKeyValue(LEFT, 'Y');
    }



    public static void inputValueWithRightKeyboard(String value) {
        inputValueWithKeyboard(RIGHT, value);
        inputKeyValue(RIGHT, 'Y');
    }


    public static void inputValueWithMiddleKeyboard(String value) {
        inputValueWithKeyboard(MIDDLE, value);
        inputKeyValue(MIDDLE, 'Y');
    }

    public static void inputValueWithTempKeyboard(String value) {
        inputValueWithKeyboard(TEMP, value);
    }

}
