package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

    private Long orderNumber;

    @Column(columnDefinition = "INT")
    private int depth;

    private boolean isParent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> childComments;

    public Comment(User user, Event event, Comment parent, Long orderNumber, int depth){
        this.user = user;
        this.event = event;
        this.parent = parent;
        this.orderNumber = orderNumber;
        if (parent == null){
            this.isParent = false;
            this.depth = 0;
        }
        else {
            this.isParent = true;
            this.depth = parent.getDepth();

        }

    }

    public void  setIsParent(boolean isParent){
        this.isParent = isParent;
    }
    public void setOrderNumber(Long orderNumber) {

        this.orderNumber = orderNumber;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }



}
