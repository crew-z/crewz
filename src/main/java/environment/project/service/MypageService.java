package environment.project.service;


import environment.project.dto.UserDTO;

import java.util.HashMap;
import java.util.List;

public interface MypageService {
    UserDTO selectUserInfoFromMypage(String userId);

    void updateUserInfo(UserDTO userDTO, String userId);

    List<HashMap<String, Object>> selectUserJoinClub(String userId);
}
