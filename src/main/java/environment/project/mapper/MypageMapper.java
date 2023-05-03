package environment.project.mapper;

import environment.project.dto.ClubInfoDTO;
import environment.project.dto.UserDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.HashMap;
import java.util.List;

@Mapper
public interface MypageMapper {

    @Select("SELECT user_no,user_id,user_name,user_tel,user_nickname,user_email FROM user WHERE user_no = #{ userNo } ")
    UserDTO selectUserInfoByUserNo(Long userNo);
    // 회원정보 불러오기
    @Select("SELECT user_no,user_id,user_name,user_tel,user_nickname,user_email FROM user WHERE user_id = #{ userId }  ")
    UserDTO selectUserInfoFromMypage(@Param("userId") String userId);

    // 회원 이메일, 닉네임, 전화번호 수정
    @Update("UPDATE user SET user_tel = #{ userTel },user_nickname = #{ userNickname }, user_email = #{ userEmail } WHERE user_no = #{ userNo } ")
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
            "WHERE user.user_no = #{ userNo } ")
    List<HashMap<String, Object>> selectUserClub(@Param("userNo") Long userNo);

    // 동아리신청 회원 정보 불러오기
    @Select("SELECT u.user_no, u.user_name, u.user_email, u.user_tel, u.user_nickname, ci.club_join_date, ca.club_name, ci.club_user_grade, ci.club_no " +
            "FROM club_info ci " +
            "JOIN club c ON ci.club_no = c.club_no " +
            "JOIN club_apply ca ON c.club_apply_no = ca.club_apply_no " +
            "JOIN user u ON ci.user_no = u.user_no " +
            "WHERE ci.club_user_grade = 0 AND c.club_no IN ( " +
            "SELECT ci2.club_no " +
            "FROM club_info ci2 " +
            "WHERE ci2.user_no = 2 ) ")
    List<HashMap<String,Object>> selectClubApplicationMemInfo(Long userNo);

    // 동아리신청 회원 수락
    @Update("UPDATE club_info SET club_user_grade = 1 , club_approve_date = now() WHERE user_no = #{ userNo } AND club_no = #{ clubNo } ")
    int updateUserClubJoin(Long userNo, Long clubNo);

    @Delete("DELETE FROM club_info WHERE user_no = #{ userNo } AND club_no = #{ clubNo }")
    int delteApplicatedUserJoinClub(Long userNo, Long clubNo);

}
