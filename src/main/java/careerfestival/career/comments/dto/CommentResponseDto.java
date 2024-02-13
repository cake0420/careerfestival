package careerfestival.career.comments.dto;

import careerfestival.career.domain.mapping.Comment;
import careerfestival.career.repository.CommentLikeRepository;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private Long userId;
    private Long eventId;
    private String commentContent;
    private Long parent;
    private String Name;
    private Integer totalLikeCount;
    private LocalDateTime createdAt;


    public CommentResponseDto(Comment comment, CommentLikeRepository commentLikeRepository) {
        this.userId = (comment.getUser() != null) ? comment.getUser().getId() : null;
        this.eventId = (comment.getEvent() != null) ? comment.getEvent().getId() : null;
        this.commentContent = comment.getCommentContent();
        this.parent = (comment.getParent() != null) ? comment.getParent().getId() : null;
        this.Name = comment.getName();
        this.totalLikeCount = commentLikeRepository.countByCommentId(comment.getId());
        this.createdAt = comment.getCreatedAt();
    }
}
