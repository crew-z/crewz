package environment.project.dto;

import lombok.*;

@Data
public class BoardReplyCreateDTO {
    private Long replyNo;
    private Long boardNo;
    private Long userNo;
    private Long clubNo;
    private String replyContent;
}
