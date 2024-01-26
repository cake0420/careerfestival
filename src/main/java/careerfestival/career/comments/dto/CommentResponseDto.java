package careerfestival.career.comments.dto;

import careerfestival.career.domain.mapping.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private Long userId;
    private Long eventId;
    private String commentContent;
    private Long parent;



    public CommentResponseDto(Comment comment) {
        this.userId = (comment.getUser() != null) ? comment.getUser().getId() : null;
        this.eventId = (comment.getEvent() != null) ? comment.getEvent().getId() : null;
        this.commentContent = comment.getCommentContent();
        this.parent = (comment.getParent() != null) ? comment.getParent().getId() : null;

    }

}
