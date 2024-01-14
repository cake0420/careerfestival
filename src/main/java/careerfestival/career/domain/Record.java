package careerfestival.career.domain;

import careerfestival.career.domain.keyword.RecordKeyword;
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
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "record")
    private List<RecordKeyword> recordKeywords = new ArrayList<>();

    // 행사명
    private String eventName;

    // 행사 일시
    private LocalDate eventDate;

    // 행사 기록
    private String eventDescription;

    // 이미지 첨부
    // private _____ eventImage;

    // 인맥 네트워킹_이름
    private String networkingName;
    // 인맥 네트워킹_전화번호 및 이메일
    private String networkingContact;
}
