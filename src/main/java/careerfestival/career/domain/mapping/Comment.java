package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, name = "comment_content")
    private String commentContent;

    @Column(length = 300, name = "parent_content")
    private String parentContent;

    @Column(columnDefinition = "INT")
    private int depth; // 댓글 깊이

    Boolean isCommentForComment; //대댓글 여부
    Long orderNumber; //댓글의 순서

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parentComment;

    public Comment(User user, Event event, Comment parentComment, String commentContent){
        this.user = user;
        this.event = event;
        this.commentContent = commentContent;
        this.parentComment = parentComment;
        if(parentComment == null){ // 부모 댓글이 없을 때
            this.isCommentForComment = false; // 대댓글이 아니다
            this.depth = 0 ; // 깊이는 0
            this.orderNumber = getId() ; //순서는 새롭게 지정
        }else{ // 부모 댓글이 존재할 때
            this.isCommentForComment = true; // 대댓글이 맞다
            this.depth = parentComment.depth+1; // 깊이는 부모의 깊이+1
            this.orderNumber = parentComment.getOrderNumber(); // 순서는 부모의 순서를 그대로 물려받는다.
        }
    }

//    public int getDepth() {
//        return depth;
//    }
//
//    public void setDepth(int depth) {
//        this.depth = depth;
//    }
}
