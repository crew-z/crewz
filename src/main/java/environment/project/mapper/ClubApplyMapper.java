package environment.project.mapper;

import environment.project.dto.ClubApplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClubApplyMapper {
    // SELECT
    @Select("SELECT club_apply_no,us.user_no,club_name,club_purpose,club_activities,club_approve_yn,club_refuse_reason,regdate,user_id,user_name,user_email FROM club_apply ca LEFT JOIN user us on ca.user_no = us.user_no") // sql문 그대로 작성
    List<ClubApplyDTO> selectService();

    @Select("SELECT club_apply_no,us.user_no,club_name,club_purpose,club_activities,club_approve_yn,club_refuse_reason,regdate,user_id,user_name,user_email" +
            " FROM club_apply ca LEFT JOIN user us on ca.user_no = us.user_no" +
            " WHERE club_approve_yn = #{approveYn}")
    List<ClubApplyDTO> selectClubApplyListToApproveYn(String approveYn);

    @Select("SELECT club_apply_no,us.user_no,club_name,club_purpose,club_activities,club_approve_yn,club_refuse_reason,regdate,user_id,user_name,user_email" +
            " FROM club_apply ca LEFT JOIN user us on ca.user_no = us.user_no" +
            " WHERE club_approve_yn IS NULL")
    List<ClubApplyDTO> selectClubApplyListToIsNull();

    // UPDATE
    @Update("UPDATE club_apply SET club_approve_yn = #{clubApproveYn},club_refuse_reason = #{clubRefuseReason},updatedate = now() WHERE club_apply_no = #{clubApplyNo} ")
    int updateService(ClubApplyDTO clubApplyDTO);
}
