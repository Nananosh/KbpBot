package KBPBot;


import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

public interface Sendable {
    void send(VkApiClient vk,GroupActor actor, int userId) throws ClientException, ApiException;
}
