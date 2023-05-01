package environment.project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClubDTO {
    private int clubNo;
    private Date clubCreateDate;

    // JoinData
    private int clubApplyNo;
}
