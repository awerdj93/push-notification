package backend.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
       private Long id;
       private Long userId;
       private String user_addr;
       private String user_email;
}
