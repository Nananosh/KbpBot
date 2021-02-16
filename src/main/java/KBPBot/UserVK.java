package KBPBot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVK {
    private int id;
    private String firstname;
    private String lastname;
    private String state;
    private String platform;
    private int last_message_id;

}
