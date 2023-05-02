package environment.project.mapper;

import environment.project.dto.ClubApplyDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClubApplyMapper {
    // SELECT for AdminPage
    @Select("SELECT club_apply_no, us.user_no, club_name, club_purpose, club_activities, club_approve_yn, club_refuse_reason, regdate, updatedate, user_id, user_name, user_email " +
            "FROM club_apply ca LEFT JOIN user us on ca.user_no = us.user_no")
    List<ClubApplyDTO> selectService();

    // UPDATE
    @Update("UPDATE club_apply SET club_approve_yn = #{clubApproveYn}, club_refuse_reason = #{clubRefuseReason}, updatedate = now() WHERE club_apply_no = #{clubApplyNo} ")
    int updateService(ClubApplyDTO clubApplyDTO);

    // INSERT // for 동아리 개설 폼
    @Insert("INSERT INTO club_apply(user_no, club_name, club_purpose, club_activities, regdate) " +
            "VALUES(#{userNo},#{clubName},#{clubPurpose},#{clubActivities},now())")
    @Options(useGeneratedKeys = true, keyProperty = "clubApplyNo")
    Long insertClubApply(ClubApplyDTO clubApplyDTO);

    // SELECT // for 개설 신청서 조회
    @Select("SELECT club_apply_no, user_no, club_name, club_purpose, club_activities, club_approve_yn, club_refuse_reason, regdate " +
            "FROM club_apply WHERE club_apply_no = #{club_apply_no}")
    ClubApplyDTO getApplicationByApplyNo(Long clubApplyNo);
}
