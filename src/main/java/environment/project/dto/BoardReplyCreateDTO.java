package environment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardReplyCreateDTO {
    private Long replyNo;
    private Long boardNo;
    private Long userNo;
    private Long clubNo;
    private String replyContent;
}
