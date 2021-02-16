import KBPBot.DBConnector;
import KBPBot.UserVK;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import com.vk.api.sdk.queries.users.UsersGetQuery;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class TestClass {

    TransportClient transportClient = new HttpTransportClient();
    VkApiClient vk = new VkApiClient(transportClient);
    Random random = new Random();
    GroupActor actor = new GroupActor(195513811,"0870ea9e4e17a19872349650ea18387d26a8638591622ccdb503c71f5a7b3fc73dc5e1b914d90de3041c3");
    @Test
    public void Test() throws SQLException, ClientException, ApiException {
//    DBConnector.changeStateVkUserFromDB("The Best",158427526);
//    System.out.println(DBConnector.getVkUserFromDB(158427526));
//    DBConnector.insertVkUserFromDB(new UserVK(3232284,"Антон","Боров","Loh","Vk",0));
//    System.out.println(vk.users().get(actor).userIds("158427526").execute());
        System.out.println(DBConnector.isVkUserInDb(158427526));

}
}
