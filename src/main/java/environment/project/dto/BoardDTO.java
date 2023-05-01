package environment.project.dto;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;


@Data
public class BoardDTO {
    private Long boardNo;
    private Long userNo;
    private String boardTitle;
    private String boardPreContent;
    private String boardContent;
    private String boardActiveContent;
    private String boardRegularContent;
    private String boardEtcContent;
    private String boardApproveYn;
    private int boardViews;
    private String recruitStatus;
    private LocalDateTime regdate;
    private LocalDateTime updatedate;
    private Date startDate;
    private Date endDate;
}
