package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import careerfestival.career.domain.mapping.RecordKeyword;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Record extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, name = "event_name")
    private String eventName;
    @Column(nullable = false, name = "event_date")
    private LocalDateTime eventDate;

    @Column(nullable = false, length = 300, name = "event_description")
    private String eventDescription;
    @Column(length = 300, name = "networking_name")
    private String networkingName;
    @Column(length = 300, name = "networking_contact")
    private String networkingContact;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
    private List<RecordKeyword> recordKeywords = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public void user(Optional<User> user){
        //checkpoint
    /* Optional.ofNullable(this.user)
                .ifPresent(oldUser -> oldUser.removeRecord(this));

        this.user = user;
        user.addRecord(this);*/
    }

}