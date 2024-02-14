package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.enums.Category;
import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.domain.mapping.*;
import careerfestival.career.participate.dto.StatisticsDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Column(nullable = false, name = "recruitment_start")
    private LocalDateTime recruitmentStart;
    @Column(nullable = false, name = "recruitment_end")
    private LocalDateTime recruitmentEnd;

    // 행사명, 간단소개,  대표이미지
    @Column(nullable = false, length = 20, name = "event_name")
    private String eventName;
    @Column(nullable = false, length = 200, name = "description")
    private String description;
    @Column(name = "event_main_file_url")
    private String eventMainFileUrl;

    // 행사 시작일, 행사 종료일, 행사 외부 사이트, 행사 정보, 행사 정보 이미지
    @Column(nullable = false, name = "event_start")
    private LocalDateTime eventStart;
    @Column(nullable = false, name = "event_end")
    private LocalDateTime eventEnd;
    @Column(length = 300, name = "link")
    private String link;
    @Column(nullable = false, length = 200, name = "event_content")
    private String eventContent;
    @Column(name = "event_inform_file_url")
    private String eventInformFileUrl;

    @Column(nullable = false, length = 40, name = "event_cost")
    private String eventCost;

    //행사 주소
    @Column(nullable = false, length = 40, name = "address")
    private String address;
    @Column(nullable = false, length = 40, name = "spec_address")
    private String specAddress;

    @Column(nullable = false, length = 20, name = "manager_name")
    private String managerName;
    @Column(length = 20, name = "manager_email")
    private String managerEmail;
    @Column(columnDefinition = "INT DEFAULT 0")
    private int hits;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private KeywordName keywordName;

    // 커리어 키워드가 기타인 경우에만 입력 받음
    @Column(length = 300, name = "event_etc_detail")
    private String eventEtcDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Organizer organizer;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inquiry> inquiry = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentLike> commentLike = new ArrayList<>();


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Wish> wish = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Participate> participate = new ArrayList<>();


    //통계자료-------------------------------------------------------------------


    private int countedMale;
    private int countedFemale;

    private int countedUnder19 ;
    private int counted20to24 ;
    private int counted25to29 ;
    private int counted30to34 ;
    private int counted35to39;
    private int countedOver40;

    private int countedCompanyType1;
    private int countedCompanyType2;
    private int countedCompanyType3 ;
    private int countedCompanyType4;
    private int countedCompanyType5;
    private int countedCompanyType6 ;

    @Transactional
    public void updateStatistics(StatisticsDto statisticsDto){
        //성별 통계 업데이트
        this.countedMale = statisticsDto.getCountedMale();
        this.countedFemale = statisticsDto.getCountedFemale();

        //나이 통계 업데이트
        this.countedUnder19 = statisticsDto.getCountedUnder19();
        this.counted20to24 = statisticsDto.getCounted20to24();
        this.counted25to29 = statisticsDto.getCounted25to29();
        this.counted30to34 = statisticsDto.getCounted30to34();
        this.counted35to39 = statisticsDto.getCounted35to39();
        this.countedOver40 = statisticsDto.getCountedOver40();

        //소속 통계 업데이트
        this.countedCompanyType1 = statisticsDto.getCountedCompanyType1();
        this.countedCompanyType2 = statisticsDto.getCountedCompanyType2();
        this.countedCompanyType3 = statisticsDto.getCountedCompanyType3();
        this.countedCompanyType4 = statisticsDto.getCountedCompanyType4();
        this.countedCompanyType5 = statisticsDto.getCountedCompanyType5();
        this.countedCompanyType6 = statisticsDto.getCountedCompanyType6();
    }
}