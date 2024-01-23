package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.enums.Category;
import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.domain.mapping.ImageData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate eventDate;
    // 기록장에서 쓰이는 행사 타이틀 (나만의)
    @Column(length = 50, name = "event_title")
    private String eventTitle;
    @Column(nullable = false, length = 300, name = "event_description")
    private String eventDescription;
    @Column(length = 300, name = "networking_name")
    private String networkingName;
    @Column(length = 300, name = "networking_contact")
    private String networkingContact;
    @Column(length = 300, name = "record_etc_detail")
    private String recordEtcDetail;

    // 기록장 (기타) 전용 topic, topicDetail
    @Column(length = 300, name = "topic")
    private String topic;
    @Column(length = 300, name = "topic_detail")
    private String topicDetail;

    @Enumerated(EnumType.STRING)
    private Category category;

    private List<KeywordName> keywordName;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(mappedBy = "record", cascade = CascadeType.ALL)
    private ImageData imageData;

    public void user(Optional<User> user){
        //checkpoint
    /* Optional.ofNullable(this.user)
                .ifPresent(oldUser -> oldUser.removeRecord(this));

        this.user = user;
        user.addRecord(this);*/
    }

}