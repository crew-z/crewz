package environment.project.dto;

import lombok.Data;

@Data
public class BoardReplyGetDTO {
	private Long replyNo;
	private Long boardNo;
	private Long userNo;
	private String replyContent;

	// userTB
	private String userId;
	private String userName;
}
