package environment.project.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ClubApplyDTO {
    private Long clubApplyNo;
    private Long userNo;
    private String clubName;
    private String clubPurpose;
    private String clubActivities;
    private String clubApproveYn;
    private String clubRefuseReason;
    private Date regdate;
    private Date updatedate;

    //JoinData
    private Long clubNo;
    private Long boardNo;
    private String userId;
    private String userName;
    private String userEmail;

}
