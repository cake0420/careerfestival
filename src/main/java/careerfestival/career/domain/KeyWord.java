package careerfestival.career.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class KeyWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="KEYWORD_ID")
    private Long id;
    @Column(length = 255) // 예: 최대 길이 255로 설정
    private String categoryName;

}
