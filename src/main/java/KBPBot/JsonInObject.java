package KBPBot;

import com.google.gson.*;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.users.User;

import java.util.List;

public class JsonInObject {
    public static String getFirstName(int userId, VkApiClient vk, GroupActor actor) throws ClientException, ApiException {
        List listName = vk.users().get(actor).userIds(""+userId).execute();
        String name = listName.get(0).toString();
        JsonParser parser = new JsonParser();
        return  parser.parse(listName.get(0).toString()).getAsJsonObject().get("first_name").getAsString();
    }
    public static String getLastName(int userId, VkApiClient vk, GroupActor actor) throws ClientException, ApiException {
        List listName = vk.users().get(actor).userIds(""+userId).execute();
        String name = listName.get(0).toString();
        JsonParser parser = new JsonParser();
        return  parser.parse(listName.get(0).toString()).getAsJsonObject().get("last_name").getAsString();
    }
}
