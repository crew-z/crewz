package environment.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardReplyUpdateDTO {
    private Long replyNo;
    private Long boardNo;
    private Long userNo;
    private String replyContent;
}
