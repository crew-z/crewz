package environment.project.service;


import environment.project.dto.UserDTO;

import java.util.HashMap;
import java.util.List;

public interface MypageService {
//    List<UserDTO> selectUserInfoFromMypage(String userId);
    UserDTO selectUserInfoFromMypage();

    void updateUserInfo(UserDTO userDTO);

    List<HashMap<String, Object>> selectUserJoinClub();
}
