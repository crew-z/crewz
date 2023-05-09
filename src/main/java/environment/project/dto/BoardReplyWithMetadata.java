package environment.project.dto;

import lombok.Data;

@Data
public class BoardReplyWithMetadata {
    private BoardReplyGetDTO reply;
    private boolean isAuthor;

    public BoardReplyWithMetadata(BoardReplyGetDTO reply, boolean isAuthor) {
        this.reply = reply;
        this.isAuthor = isAuthor;
    }
}
