package util;

import page.BasePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class keyBoard extends BasePage {
    private static Map<String, KeyBoardLayout> keyBoards = new HashMap<>();

    static{
        KeyBoardLayout keyBoardLayout1 = new KeyBoardLayout();
        keyBoardLayout1.setLeft(100).setTop(457);
        keyBoards.put("Lefe", keyBoardLayout1);

        KeyBoardLayout keyBoardLayout2 = new KeyBoardLayout();
        keyBoardLayout2.setLeft(100).setTop(457);
        keyBoards.put("Middle", keyBoardLayout2);

        KeyBoardLayout keyBoardLayout3 = new KeyBoardLayout();
        keyBoardLayout3.setLeft(100).setTop(457);
        keyBoards.put("Right", keyBoardLayout3);

        KeyBoardLayout keyBoardLayout4 = new KeyBoardLayout();
        keyBoardLayout4.setLeft(100).setTop(457);
        keyBoards.put("Temp", keyBoardLayout4);
    }

    private static void inputKeyValue(String keyboardName, char keyValue){
        int x = keyBoards.get(keyboardName).getX_Coordinate(keyValue);
        int y = keyBoards.get(keyboardName).getY_Coordinate(keyValue);
        touchKeyboard(x, y);
    }

    private static void inputValueWithKeyboard(String keyboardName, String value) {
        List<Character> valueL = value.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());

        for (Character keyValue : valueL) {
            inputKeyValue(keyboardName, keyValue);
        }
    }


    public static void inputValueWithLeftKeyboard(String value) {
        inputValueWithKeyboard("Left", value);
        inputKeyValue("Left", 'Y');
    }



    public static void inputValueWithRightKeyboard(String value) {
        inputValueWithKeyboard("Right", value);
        inputKeyValue("Right", 'Y');
    }


    public static void inputValueWithMiddleKeyboard(String value) {
        inputValueWithKeyboard("Middle", value);
        inputKeyValue("Middle", 'Y');
    }

    public static void inputValueWithTempKeyboard(String value) {
        inputValueWithKeyboard("Temp", value);
    }

}
