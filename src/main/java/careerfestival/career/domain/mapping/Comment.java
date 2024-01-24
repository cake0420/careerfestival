package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, name = "comment_content")
    private String commentContent;

    @Column(columnDefinition = "LONG")
    private Long orderNumber;

    @Column(columnDefinition = "INT")
    private int depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> childComments;

    public Comment(User user, Event event, Comment parent, Long orderNumber, int depth){
        this.user = user;
        this.event = event;
        this.parent = parent;
        this.orderNumber = orderNumber;
        if (parent != null) {
            this.depth = parent.getDepth() + 1;
        } else {
            this.depth = 0;
        }        this.childComments = new ArrayList<>();
    }
    public void addChildComment(Comment childComment) {
        childComments.add(childComment);
        childComment.setParent(this);
    }
    public void setParent(Comment parent) {
        this.parent = parent;
    }
    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }


}
