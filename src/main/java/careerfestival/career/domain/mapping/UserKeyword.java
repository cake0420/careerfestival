package careerfestival.career.domain.mapping;

import careerfestival.career.domain.User;
import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.enums.KeywordName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserKeyword extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private KeywordName keywordName;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
