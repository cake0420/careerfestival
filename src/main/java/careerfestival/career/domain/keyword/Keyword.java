package careerfestival.career.domain.keyword;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="KEYWORD_ID")
    private Long id;

    // 행사 분야 13개의 값
    private KeywordName keywordName;
}
