package environment.project.service;


import environment.project.dto.UserDTO;

import java.util.HashMap;
import java.util.List;

public interface MypageService {
    UserDTO selectUserInfoFromMypage(String userId);

    void updateUserInfo(UserDTO userDTO);

    List<HashMap<String, Object>> selectUserJoinClub(Long userNo);

    UserDTO selectUserInfoByUserNo(Long userNo);
}
