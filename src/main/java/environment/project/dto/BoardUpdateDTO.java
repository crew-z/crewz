package environment.project.dto;

import lombok.*;

@Data
public class BoardUpdateDTO {
    private Long boardNo;
    private Long userNo;
    private Long clubNo;
    private String boardTitle;
    private String boardPreContent;
    private String boardContent;
    private String boardActiveContent;
    private String boardRegularContent;
    private String boardEtcContent;
}
