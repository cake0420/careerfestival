package careerfestival.career.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class EventInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 행사 시작일, 행사 종료일, 행사 외부 사이트, 행사 정보, 행사 정보 이미지
    @Column(nullable = false, name="EVENTSTART")
    private LocalDate eventStart;
    @Column(nullable = false,name ="EVENTEND")
    private LocalDate eventEnd;
    @Column(nullable = false, length = 300,name="LINK")
    private String link;
    @Column(nullable = false, length = 200,name ="EVENTCONTENT")
    private String eventContent;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventImage> eventImg = new ArrayList<>();

    private String managerName;
    private String managerEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
