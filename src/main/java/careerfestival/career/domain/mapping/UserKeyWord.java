package careerfestival.career.domain.mapping;

import careerfestival.career.domain.User;
import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.enums.KeywordName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserKeyWord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private KeywordName keywordName;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;



}
