package KBPBot;


import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

import java.sql.SQLException;
import java.util.Arrays;

public class MessageHandler {
    private GroupActor actor;
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
                            "/notes - открыть заметки\n",KeyBoardFactory.createKeyboard(3      , Arrays.asList("/info","/subscribe","/get","/see","/notify","/notes"))).send(vk,actor,message.getFromId());
                    break;
                }else{
                    System.out.print("Net");
                    DBConnector.insertVkUserInDB(UserVkFactory.userVk(message.getFromId(),vk,actor,"Лох","Vk",0));
                    MessageFactory.createMessage("Привет!\nЭтот бот поможет смотреть расписание твоего любимого колледжа!\nЧтобы начать необходимо ввести одну из команд ниже:\n" +
                            "/info - информация о боте и его командах (ты тут)\n" +
                            "/subscribe - подписаться на один из источников (твоя группа именно здесь)\n" +
                            "/get - получить расписание одной из своих подписок\n" +
                            "/see - посмотреть другое расписание, не подписываясь на него\n" +
                            "/notify - настроить ежедневную рассылку расписания из твоих подписок\n" +
                            "/notes - открыть заметки\n",KeyBoardFactory.createKeyboard(3      ,Arrays.asList("/info","/subscribe","/get","/see","/notify","/notes"))).send(vk,actor,message.getFromId());
                    break;
                }
            case "/subscribe":
                System.out.print("Podpiska");
                MessageFactory.createMessage("Выберите параметр по которому вы хотите подписаться",KeyBoardFactory.createKeyboard(2,Arrays.asList("Группа","Преподаватель", "Предмет","Аудитория"))).send(vk,actor,message.getFromId());
                break;
            case "Группа":
                MessageFactory.createMessage("Выберите специальность",KeyBoardFactory.createKeyboard(4,DBConnector.getAllSpecialsInDB("group"))).send(vk,actor, message.getFromId());
                break;
            case "Т":
                MessageFactory.createMessage("Выберите свою группу",KeyBoardFactory.createKeyboard(3,DBConnector.getAllSourcesInDB("Т","group"))).send(vk,actor, message.getFromId());
                break;
            case "Т-917":
                DBConnector.addSourceUserConnectionsFromDB(message.getText(),message.getFromId());
                break;
        }
    }
}
