package environment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardGetDTO {
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

    // board_period TB
    private Date startDate;
    private Date endDate;
}
