package environment.project.dto;

import lombok.*;

@Data
public class BoardReplyUpdateDTO {
    private Long replyNo;
    private Long boardNo;
    private Long userNo;
    private String replyContent;
}
