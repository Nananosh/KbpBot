package KBPBot;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;

public class Bot {
    public static void main(String[] args)throws ClientException,ApiException,InterruptedException
    {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        GroupActor actor = new GroupActor(195513811,"0870ea9e4e17a19872349650ea18387d26a8638591622ccdb503c71f5a7b3fc73dc5e1b914d90de3041c3");
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        Keyboard keyboard = new Keyboard();
        keyboard = KeyBoardFactory.createKeyboard(3,"Т","П","Т");
        int i = 0;
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
                        if (message.getText().equals("Начать")) {
                            vk.messages().send(actor).message("Ааааа").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                            vk.messages().send(actor).message("Выберите вашу специальность").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }else if(message.getText().equals("Т")){

                        }
                    }
                    catch (ApiException | ClientException e ){e.printStackTrace();}
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(300);
        }

    }
}
