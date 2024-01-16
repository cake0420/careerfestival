package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.enums.KeywordName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordKeyword extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORDKEYWORD_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_ID")
    private Record record;

    @Enumerated(EnumType.STRING)
    private KeywordName keywordName;
}