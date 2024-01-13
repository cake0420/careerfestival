package careerfestival.career.domain;


import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.mapping.EventKeyword;
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
    private Long id;

    // 모집 시작일 & 모집 종료일
    @Column(nullable = false, name="RECRUITMENTSTART")
    private LocalDate recruitmentStart;
    @Column(nullable = false, name="RECRUITMENTEND")
    private LocalDate recruitmentEnd;

    // 행사명, 간단소개,  대표이미지
    @Column(nullable = false, length = 20 ,name ="EVENTNAME")
    private String eventName;
    @Column(nullable = false, length = 200,name="SELFINTRO")
    private String selfIntro;
    @Column(nullable = false, length = 300, name="MAINIMG")
    private String mainImg;



    //checkpoint 주최자랑 연결?
    //회원 id 연결 주최자와
    private String managerName;
    private String managerEmail;
    private int hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventImage> eventImg = new ArrayList<>();

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private EventAddress eventAddress;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private EventInformation eventInformation;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventKeyword> eventKeyword = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Wish> wish = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<RecordKeyWord> recordKeyWord = new ArrayList<>();
}
