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
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 모집 시작일 & 모집 종료일
    @Column(nullable = false, name = "RECRUITMENTSTART")
    private LocalDate recruitmentStart;
    @Column(nullable = false, name = "RECRUITMENTEND")
    private LocalDate recruitmentEnd;

    // 행사명, 간단소개,  대표이미지
    @Column(nullable = false, length = 20, name = "EVENTNAME")
    private String eventName;
    @Column(nullable = false, length = 200, name = "DESCRIPTION")
    private String description;
    @Column(nullable = false, length = 300, name = "MAINIMG")
    private String mainImg;

    // 행사 시작일, 행사 종료일, 행사 외부 사이트, 행사 정보, 행사 정보 이미지
    @Column(nullable = false, name = "EVENTSTART")
    private LocalDate eventStart;
    @Column(nullable = false, name = "EVENTEND")
    private LocalDate eventEnd;
    @Column(nullable = false, length = 300, name = "LINK")
    private String link;
    @Column(nullable = false, length = 200, name = "EVENTCONTENT")
    private String eventContent;

    //행사 주소
    @Column(nullable = false, length = 40, name ="ADDRESS")
    private String address;
    @Column(nullable = false, length = 40, name ="SPECADDRESS")
    private String specAddress;



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

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventKeyword> eventKeyword = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Wish> wish = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<RecordKeyWord> recordKeyWord = new ArrayList<>();

}