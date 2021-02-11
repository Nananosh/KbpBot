package KBPBot;

import com.vk.api.sdk.objects.messages.*;

import java.security.Key;
import java.util.*;

public class KeyBoardFactory {
//    Генерация кнопки для клавиатуры
    private static KeyboardButton createKeyboardButton(String text, KeyboardButtonColor keyboardButtonColor){
        return new KeyboardButton()
                .setAction(new KeyboardButtonAction()
                        .setLabel(text)
                        .setType(TemplateActionTypeNames.TEXT)).setColor(keyboardButtonColor);
    }
//    Генерация линии для клавиатуры
//    private static List<KeyboardButton> createKeyboarRow(KeyboardButtonColor keyboardButtonColor,String... text){
//        List<KeyboardButton> row = new ArrayList<>();
//        for(String buttonText:text) {
//            row.add(createKeyboardButton(buttonText, keyboardButtonColor));
//        }
//        return row;
//    }
//    Генерация списка слов со всеми кнопками
    private static List<List<KeyboardButton>> createRowList(int colonnCount,String... text){
        Queue<String> textQueue = new ArrayDeque<>();
        for(String buttonName:text){
            textQueue.add(buttonName);
        }
        boolean colorsType = false;
        List<List<KeyboardButton>> rowList = new ArrayList<>();
        boolean state = true;
        while(state){
            List<KeyboardButton> row = new ArrayList<>();
            for (int i = 0; i < colonnCount; i++) {
                if(textQueue.peek()!=null){
                    KeyboardButtonColor keyboardButtonColor;
                    if(colorsType){
                        keyboardButtonColor = KeyboardButtonColor.PRIMARY;
                    }
                    else{
                        keyboardButtonColor = KeyboardButtonColor.DEFAULT;
                    }
                    row.add(createKeyboardButton(textQueue.poll(),keyboardButtonColor));
                }else{state=false;}
            }
            rowList.add(row);
            colorsType=!colorsType;
        }
        return rowList;
    }
    public static Keyboard createKeyboard(int colonnCount,String... text){
        return new Keyboard().setButtons(createRowList(colonnCount,text));
    }
}

