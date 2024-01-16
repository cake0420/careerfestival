package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

public class Host extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, length = 20, name = "host_name")
    private String hostName;

    @Column(nullable = false, length = 300, name = "profile_img")
    private String profileImg;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
