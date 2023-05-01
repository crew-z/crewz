package environment.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
