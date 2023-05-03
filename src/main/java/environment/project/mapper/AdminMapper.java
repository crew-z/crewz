package environment.project.mapper;

import environment.project.dto.ClubDTO;
import environment.project.dto.ClubInfoDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface AdminMapper {
    //동아리 생성
    @Insert("INSERT INTO club (club_apply_no,club_create_date) VALUES (#{clubApplyNo}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "clubNo", keyColumn="club_no")
    int createClub(ClubDTO clubDTO);
    //최초의 동아리 장 생성
    @Insert("INSERT INTO club_info (club_no,user_no,club_user_grade,club_join_date) VALUES (#{clubNo}, #{userNo}, #{clubUserGrade}, now() )")
    @Options(useGeneratedKeys = true, keyProperty = "idx", keyColumn="idx")
    int createClubInfo(ClubInfoDTO clubInfoDTO);

}
