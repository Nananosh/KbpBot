package KBPBot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Keyboard;

import static KBPBot.DBConnector.getNextMessageId;


public class BotMessage implements Sendable {
    private String textMessage;
    private Keyboard keyboard;

    public BotMessage(String textMessage, Keyboard keyboard) {
        this.textMessage = textMessage;
        this.keyboard = keyboard;
    }

    @Override
    public void send(VkApiClient vk, GroupActor actor, int userId) throws ClientException, ApiException {
        vk.messages().send(actor).message(textMessage).userId(userId).randomId(getNextMessageId()).keyboard(keyboard).execute();
    }
}
