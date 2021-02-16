package KBPBot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

import java.sql.SQLException;

public class MessageHandler {
    private GroupActor actor;
    private UserActor userActor = new UserActor(158427526,"0870ea9e4e17a19872349650ea18387d26a8638591622ccdb503c71f5a7b3fc73dc5e1b914d90de3041c3");
    private VkApiClient vk;

    public MessageHandler(GroupActor actor, VkApiClient vk) {
        this.actor = actor;
        this.vk = vk;
    }

    public void hadnle(Message message) throws SQLException, ClientException, ApiException {
        String textMessage = message.getText();
        switch (textMessage){
            case "Начать":
                if(DBConnector.isVkUserInDb(message.getFromId())){
                    System.out.println("Da");
                    MessageFactory.createMessage("Привет!\nЭтот бот поможет смотреть расписание твоего любимого колледжа!\nЧтобы начать необходимо ввести одну из команд ниже:\n" +
                            "/info - информация о боте и его командах (ты тут)\n" +
                            "/subscribe - подписаться на один из источников (твоя группа именно здесь)\n" +
                            "/get - получить расписание одной из своих подписок\n" +
                            "/see - посмотреть другое расписание, не подписываясь на него\n" +
                            "/notify - настроить ежедневную рассылку расписания из твоих подписок\n" +
                            "/notes - открыть заметки\n",KeyBoardFactory.createKeyboard(5,"/info","/subscribe","/get","/see","/notify","/notes")).send(vk,actor,message.getFromId());
                }else{
                    System.out.println("Нет");
                }
        }
    }
}
