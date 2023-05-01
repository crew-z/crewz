package environment.project.mapper;

import environment.project.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.HashMap;
import java.util.List;

@Mapper
public interface MypageMapper {
//    @Select("SELECT user_id,user_name,user_tel,user_nickname,user_email FROM user WHERE user_id = #{ userId } ")
//    List<UserDTO> selectUserInfoFromMypage(@Param("userId") String userId);

    // 회원정보 불러오기
    @Select("SELECT user_id,user_name,user_tel,user_nickname,user_email FROM user WHERE user_id = 'janedoe456'  ")
    UserDTO selectUserInfoFromMypage();

    // 회원 이메일, 닉네임, 전화번호 수정
    @Update("UPDATE user SET user_tel = #{ userTel },user_nickName = #{ userNickname }, user_email = #{ userEmail } WHERE user_id = 'janedoe456' ")
    void updateUserInfo(UserDTO userDTO);


//    @Select("SELECT user_name, club_name FROM club " +
//            "JOIN club_info ON club.club_no = club_info.club_no " +
//            "JOIN user ON club_info.user_no = user.user_no " +
//            "JOIN club_apply ON club_apply.club_apply_no = club.club_apply_no " +
//            "WHERE user_id = #{ userId } ")
//    List<HashMap<String, Object>> selectUserClub(@Param("userId") String userId);

    // 회원 동아리 정보 불러오기
    @Select("SELECT user_name, club_name, club_activities FROM club " +
            "JOIN club_info ON club.club_no = club_info.club_no " +
            "JOIN user ON club_info.user_no = user.user_no " +
            "JOIN club_apply ON club_apply.club_apply_no = club.club_apply_no " +
            "WHERE user_id = 'janedoe456' ")
    List<HashMap<String, Object>> selectUserClub();

    // 동아리신청 회원 정보 불러오기
    @Select("SELECT user_name, club_join_date,user_email,user_nickname,user_tel FROM club_info JOIN user ON club_info.user_no = user.user_no WHERE user_id = 'janedoe456'")
    List<HashMap<String,Object>> selectClubApplicationMemInfo();
}
