package environment.project.service;


import environment.project.dto.ClubInfoDTO;
import environment.project.dto.UserDTO;

import java.util.HashMap;
import java.util.List;

public interface MypageService {
    UserDTO selectUserInfoFromMypage(String userId);

    void updateUserInfo(UserDTO userDTO);


    List<HashMap<String, Object>> selectUserJoinClub(Long userNo);

    UserDTO selectUserInfoByUserNo(Long userNo);

    List<HashMap<String, Object>> selectUserJoinClub();
    int updateClubMemGrade(Long userNo, Long clubNo);

    int delteApplicatedUserInfo(Long userNo, Long clubNo);

}
