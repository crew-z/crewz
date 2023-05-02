package environment.project.dto;

import lombok.Data;


@Data
public class UserDTO {
    private Long userNo;
    private String userId;
    private String userPassword;
    private String userName;
    private String userTel;
    private String userNickname;
    private String userEmail;
}
