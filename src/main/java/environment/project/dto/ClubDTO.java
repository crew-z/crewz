package environment.project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ClubDTO {
    private Long clubNo;
    private Date clubCreateDate;

    // JoinData
    private Long clubApplyNo;
}
