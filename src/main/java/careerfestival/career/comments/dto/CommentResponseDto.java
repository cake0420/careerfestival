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
    //    private String parentContent;
//    private int depth;
    public CommentResponseDto(Comment comment) {
        this.userId = comment.getUser().getId();
        this.eventId = comment.getEvent().getId();
        this.commentContent = comment.getCommentContent();
//        this.parentContent = (comment.getParentComment() != null) ? comment.getParentComment().getCommentContent() : null;
//        this.depth = comment.getDepth();
    }
}
