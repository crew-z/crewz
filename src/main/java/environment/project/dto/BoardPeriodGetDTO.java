package environment.project.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardPeriodGetDTO {
    private Date startDate;
    private Date endDate;
}
