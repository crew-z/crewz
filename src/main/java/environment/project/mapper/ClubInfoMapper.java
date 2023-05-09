package environment.project.mapper;

import environment.project.dto.ClubInfoCreateDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClubInfoMapper {

    // 동아리 가입 신청
    @Insert("INSERT INTO club_info (club_no, user_no, club_join_date) " +
            "VALUES (#{clubNo}, #{userNo}, now())")
    void insertClubInfo(ClubInfoCreateDTO clubInfoCreateDTO);

    // 이미 가입 신청 했는지 CHECK
    @Select("SELECT COUNT(idx) FROM club_info WHERE club_no = #{clubNo} AND user_no = #{userNo}")
    int checkUserInClub(@Param("clubNo") Long clubNo, @Param("userNo") Long userNo);
}