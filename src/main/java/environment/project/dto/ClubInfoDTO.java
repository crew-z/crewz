package environment.project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClubInfoDTO {
    private int idx;
    private int clubUserGrade;
    private boolean clubUsed;
    private Date clubJoinDate;
    private Date clubApproveDate;
    private Date clubOutDate;

    // JoinData
    private int clubNo;
    private int userNo;
}
