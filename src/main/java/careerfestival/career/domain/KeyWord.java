package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.mapping.EventKeyword;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class KeyWord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="KEYWORD_ID")
    private Long id;

    @Column(length = 255) // 예: 최대 길이 255로 설정
    private String categoryName;

    @OneToMany(mappedBy = "keyWord", cascade = CascadeType.ALL)
    private List<EventKeyword> eventKeyword = new ArrayList<>();

    @OneToMany(mappedBy = "keyWord", cascade = CascadeType.ALL)
    private List<RecordKeyWord> recordKeyWord = new ArrayList<>();
}
