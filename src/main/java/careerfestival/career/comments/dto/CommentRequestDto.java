package careerfestival.career.comments.dto;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequestDto {

    private Long userId;
    private Long eventId;
    private Long parent;  // Updated to Long type
    private String commentContent;
    private  Long orderNumber;



    @Builder
    public static CommentRequestDto of(Long userId, Long eventId, String commentContent, Long parent, Long orderNumber) {
        return CommentRequestDto.builder()
                .userId(userId)
                .eventId(eventId)
                .commentContent(commentContent)
                .parent(parent)
                .orderNumber(orderNumber)
                .build();
    }

    public Comment toEntity(User user, Event event, String commentContent) {
        return Comment.builder()
                .user(user)
                .event(event)
                .commentContent(commentContent)
                .build();
    }

    public Comment toEntityWithParent(User user, Event event, String commentContent, Comment parent) {
        return Comment.builder()
                .user(user)
                .event(event)
                .commentContent(commentContent)
                .parent(parent)
                .depth(parent.getDepth() + 1)
                .build();
    }
}
