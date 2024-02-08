package careerfestival.career.domain.mapping;

import careerfestival.career.domain.User;
import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscribe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 구독하려는 사람
    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;

    // 구독되는 주최자
    @ManyToOne
    @JoinColumn(name = "subscribed_organizer")
    private Organizer subscribedOrganizer;

    public Subscribe(User toUser, Organizer subscribedOrganizer){
        this.toUser = toUser;
        this.subscribedOrganizer = subscribedOrganizer;
    }
    public Long getSubscribedOrganizer() {
        return subscribedOrganizer.getId();
    }

    public Long getToUser() {
        return toUser.getId();
    }

}
