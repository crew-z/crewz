package environment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
