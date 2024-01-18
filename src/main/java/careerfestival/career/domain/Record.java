package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // category enums
    @Enumerated(EnumType.STRING)
    private String category;

    // keyword enums
    @Enumerated(EnumType.STRING)
    private String keyword;

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


    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}