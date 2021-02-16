package KBPBot;

import com.vk.api.sdk.objects.messages.Keyboard;



public class MessageFactory {
    public static BotTextMessage createMessage(String text, Keyboard keyboard){
        return new BotTextMessage(text,keyboard);
    }
}
