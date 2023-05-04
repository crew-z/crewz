package environment.project.mapper;

import environment.project.dto.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.HashMap;
import java.util.List;

@Mapper
public interface MypageMapper {

    // 회원정보 불러오기
    @Select("SELECT user_no,user_id,user_name,user_tel,user_nickname,user_email FROM user WHERE user_no = #{ userNo } ")
    UserDTO selectUserInfoByUserNo(Long userNo);

    // 회원 이메일, 닉네임, 전화번호 수정
    @Update("UPDATE user SET user_tel = #{ userTel },user_nickname = #{ userNickname }, user_email = #{ userEmail } WHERE user_no = #{ userNo } ")
    void updateUserInfo(UserDTO userDTO);

    // 유저가 활동중인 동아리 정보 불러오기
    @Select("SELECT user_name, club_name, club_activities, club_user_grade, club.club_no, board.board_no FROM club " +
            "            JOIN club_info ON club.club_no = club_info.club_no " +
            "            JOIN user ON club_info.user_no = user.user_no  " +
            "            JOIN club_apply ON club_apply.club_apply_no = club.club_apply_no " +
            "            JOIN board ON club.club_no = board.club_no " +
            "            WHERE user.user_no = #{ userNo } AND club_info.club_user_grade = #{ clubUserGrade }")
    List<HashMap<String, Object>> selectUserClub(@Param("userNo") Long userNo, @Param("clubUserGrade") int clubUserGrade);

    // 동아리 신청현황 조회
    @Select("SELECT club_apply_no,club_name,club_purpose,club_activities, club_approve_yn FROM club_apply WHERE user_no = #{ userNo }")
    List<ClubApplyDTO> selectClubApproveResult(Long userNo);

    // 동아리장인 경우에 동아리별로 동아리페이지 보기
    @Select("SELECT ca.club_name, ci.club_user_grade, ci.user_no, c.club_no " +
            "FROM club_info ci " +
            "LEFT JOIN club c ON ci.club_no = c.club_no " +
            "LEFT JOIN club_apply ca ON c.club_apply_no = ca.club_apply_no " +
            "WHERE ci.user_no = #{ userNo } " +
            "GROUP BY ca.club_name, ci.club_user_grade, c.club_no ")
    List<HashMap<String, Object>> selectLeaderGradeByClubName(Long userNo);

    // 동아리 승인페이지 동아리이름 가져오기
    @Select("SELECT ca.club_name " +
            "FROM club_info ci " +
            "LEFT JOIN club c ON ci.club_no = c.club_no " +
            "LEFT JOIN club_apply ca ON c.club_apply_no = ca.club_apply_no " +
            "WHERE c.club_no = #{clubNo} " +
            "GROUP BY ca.club_name")
    ClubNameDTO selectClubName(Long clubNo);



    // 동아리신청 회원 정보 불러오기
    @Select("SELECT u.user_no, u.user_name, u.user_email, u.user_tel, u.user_nickname, ci.club_join_date, ca.club_name, ci.club_user_grade, ci.club_no " +
            "FROM club_info ci " +
            "JOIN club c ON ci.club_no = c.club_no " +
            "JOIN club_apply ca ON c.club_apply_no = ca.club_apply_no " +
            "JOIN user u ON ci.user_no = u.user_no " +
            "WHERE ci.club_user_grade = #{ clubUserGrade } AND c.club_no = #{ clubNo }")
    List<HashMap<String,Object>> selectClubApplicationMemInfo(@Param("clubUserGrade") int clubUserGrade, @Param("clubNo") Long clubNo);

    // 동아리신청 회원 수락
    @Update("UPDATE club_info SET club_user_grade = 1 , club_approve_date = now() WHERE user_no = #{ userNo } AND club_no = #{ clubNo } ")
    int updateUserClubJoin(@Param("userNo") Long userNo, @Param("clubNo") Long clubNo);

    @Delete("DELETE FROM club_info WHERE user_no = #{ userNo } AND club_no = #{ clubNo }")
    int delteApplicatedUserJoinClub(@Param("useNo") Long userNo, @Param("clubNo") Long clubNo);

}
