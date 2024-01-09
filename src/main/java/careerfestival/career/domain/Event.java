package careerfestival.career.domain;


import careerfestival.career.domain.common.BaseEntity;
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
//@DynamicUpdate
//@DyamicInsert
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Long id;
    @Column(nullable = false, name="RECRUITMENTSTART")
    private LocalDate recruitmentStart;
    @Column(nullable = false, name="RECRUITMENTEND")
    private LocalDate recruitmentEnd;
    @Column(nullable = false, length = 20 ,name ="EVENTNAME")
    private String eventName;
    @Column(nullable = false, length = 200,name="SELFINTRO")
    private String selfIntro;
    @Column(nullable = false, length = 40, name ="ADDRESS")
    private String address;
    @Column(nullable = false, length = 40, name ="SPECADDRESS")
    private String specAddress;
    @Column(nullable = false, length = 300, name="MAINIMG")
    private String mainImg;
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
    //checkpoint 주최자랑 연결?
    //회원 id 연결 주최자와
    private String managerName;
    private String managerEmail;
    private int hits;
    //checkpoint 행사 연결해주기


}
