package environment.project.service;


import environment.project.dto.ClubInfoDTO;
import environment.project.dto.UserDTO;

import java.util.HashMap;
import java.util.List;

public interface MypageService {

    UserDTO selectUserInfoByUserNo(Long userNo);

    void updateUserInfo(UserDTO userDTO);

    List<HashMap<String, Object>> selectUserJoinClub(Long userNo);

    List<HashMap<String,Object>> selectClubApplicatedMemList(int clubUserGrade, Long clubNo);

    List<HashMap<String, Object>> checkMemGrade(Long userNo);

    int updateClubMemGrade(Long userNo, Long clubNo);

    int delteApplicatedUserInfo(Long userNo, Long clubNo);

}
