package environment.project.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
public class BoardGetDTO {
    private Long boardNo;
    private Long userNo;
    private Long clubNo;
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
}
