package environment.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class UserDTO {
    private int userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userTel;
    private String userNickname;
    private String userEmail;
}
