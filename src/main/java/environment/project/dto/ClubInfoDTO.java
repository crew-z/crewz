package environment.project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClubInfoDTO {
    private Long idx;
    private Long clubUserGrade;
    private boolean clubUsed;
    private Date clubJoinDate;
    private Date clubApproveDate;
    private Date clubOutDate;

    // JoinData
    private Long clubNo;
    private Long userNo;
}
