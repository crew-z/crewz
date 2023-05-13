package environment.project.dto;

import lombok.Data;

@Data
public class BoardCategoryDTO {
    Long idx;
    Long boardNo;
    Long categoryNo;
    String categoryName;
    String categoryCreateDate;
}
