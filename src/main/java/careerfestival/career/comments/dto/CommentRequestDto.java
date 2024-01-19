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
    private String commentContent;
//    private Long parentCommentId;
//    private int depth;

    @Builder
    public static CommentRequestDto of(Long userId, Long eventId, String commentContent) {
        return CommentRequestDto.builder()
                .userId(userId)
                .eventId(eventId)
                .commentContent(commentContent)
//                .parentCommentId(parentCommentId)
//                .depth(depth)
                .build();
    }

    public Comment toEntity(User user, Event event, String commentContent) {

//        Comment parentComment = null;
//        if (parentCommentId != null) {
//            // 부모 댓글이 있는 경우
//            parentComment = Comment.builder().id(parentCommentId).build();
//        }

        // toEntity 메서드를 통해 Comment 엔티티 생성
        return Comment.builder()
                .user(user)
                .event(event)
                .commentContent(commentContent)
//                .parentComment(parentComment)
//                .depth(depth)
                .build();
    }


}
