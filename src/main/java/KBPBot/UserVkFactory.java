package KBPBot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

public class UserVkFactory {
    public static UserVk userVk(int id, VkApiClient vk, GroupActor actor, String state, String platform, int last_message_id) throws ClientException, ApiException {
        return new UserVk(id, JsonInObject.getFirstName(id,vk,actor), JsonInObject.getLastName(id,vk,actor),state,platform,last_message_id);
    }
}
