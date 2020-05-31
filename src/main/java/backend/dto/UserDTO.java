package backend.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
       private Long id;
       private Long userId;
       private String user_name;
       private String user_addr;
       private String user_email;

       public void setUserId(long userId) {
              this.userId=userId;

       }

       public void setUser_addr(String user_addr) {
              this.user_addr=user_addr;
       }
}
