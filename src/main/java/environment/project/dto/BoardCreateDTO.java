package environment.project.dto;

import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
public class BoardCreateDTO {
    private Long boardNo;
    private Long userNo;
    private Long clubNo;
    private String boardTitle;
    private String boardPreContent;
    private String boardContent;
    private String boardActiveContent;
    private String boardRegularContent;
    private String boardEtcContent;

    // board_period TB
    private Date startDate;
    private Date endDate;

    // category_info TB
    private List<Long> categoryInfo;
}
