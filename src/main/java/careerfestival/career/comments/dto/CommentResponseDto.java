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
    private Long id;
    private String commentContent;
    private String parentContent;
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.commentContent = comment.getCommentContent();
        this.parentContent = comment.getParentContent();
    }
}
