package environment.project.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CategoryInfoDTO {
    Long categoryNo;
    String categoryName;
    String categoryDeleteFlag;
    Date categoryCreateDate;
}
