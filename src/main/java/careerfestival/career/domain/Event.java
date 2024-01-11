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

    // 모집 시작일 & 모집 종료일
    @Column(nullable = false, name="RECRUITMENTSTART")
    private LocalDate recruitmentStart;
    @Column(nullable = false, name="RECRUITMENTEND")
    private LocalDate recruitmentEnd;

    // 행사명, 간단소개, 주소, 상세주소, 대표이미지
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

    //checkpoint 주최자랑 연결?
    //회원 id 연결 주최자와
    private String managerName;
    private String managerEmail;
    private int hits;

    //checkpoint 행사 연결해주기


}
