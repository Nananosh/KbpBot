package KBPBot;
import java.sql.SQLException;
import java.util.*;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;

import static KBPBot.DBConnector.*;

public class Bot {
    public static void main(String[] args)throws ClientException,ApiException,InterruptedException
    {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        GroupActor actor = new GroupActor(195513811,"0870ea9e4e17a19872349650ea18387d26a8638591622ccdb503c71f5a7b3fc73dc5e1b914d90de3041c3");
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        MessageHandler botsMessage = new MessageHandler(actor,vk);
        while (true)
        {
            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            if(!messages.isEmpty())
            {
                messages.forEach(message ->
                {
                    System.out.println(message.toString());
                   try {
////                        if (message.getText().equals("Начать")) {
////                            (new BotTextMessage("Выберите вашу специальность",KeyBoardFactory.createKeyboard(4,(String[]) getAllSpecial().toArray() ))).send(vk,actor,message.getFromId());
////                        }else if(getAllSpecial().contains(message.getText())){
////                            (new BotTextMessage("Выберите вашу группу",KeyBoardFactory.createKeyboard(4,getAllGroup(message.getText())))).send(vk,actor,message.getFromId());
////                        }   
                       botsMessage.hadnle(message);
                   }
                    catch (SQLException e ){e.printStackTrace();} catch (ClientException e) {
                       e.printStackTrace();
                   } catch (ApiException e) {
                       e.printStackTrace();
                   }
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(100);
        }

    }
}
