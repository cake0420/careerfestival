package careerfestival.career.domain;


import careerfestival.career.domain.common.BaseEntity;
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
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Long id;

    private String category;

    // 모집 시작일 & 모집 종료일
    private LocalDate recruitmentStart;
    private LocalDate recruitmentEnd;

    // 행사명, 간단소개, 주소, 상세주소, 대표이미지
    private String eventName;
    private String description;
    private String address;

    private String specAddress;
    private String mainImg;

    // 행사 시작일, 행사 종료일, 행사 외부 사이트, 행사 정보, 행사 정보 이미지
    private LocalDate eventStart;
    private LocalDate eventEnd;
    private String link;
    private String eventContent;

//    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
//    private List<EventImage> eventImg = new ArrayList<>();

    // 단순하게 입력받고 보여주는 용도
    private String managerName;
    private String managerEmail;

    // 조회 수
    private int hits;

    //checkpoint 행사 연결해주기


}
