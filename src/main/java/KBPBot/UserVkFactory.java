package KBPBot;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

public class UserVkFactory {
    public static UserVK userVk(int id, VkApiClient vk, GroupActor actor,String state, String platform, int last_message_id) throws ClientException, ApiException {
        return new UserVK(id,JsonFromObject.getFirstName(id,vk,actor),JsonFromObject.getLastName(id,vk,actor),state,platform,last_message_id);
    }
}
