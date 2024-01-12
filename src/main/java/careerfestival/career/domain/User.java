package careerfestival.career.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    // 시큐리티 import하고, @NotNull 붙이기
    private String name;

    @NotNull
    private String email;
    private String password;

    private String phoneNumber;
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<InterestRegion> interestRegion = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int age;

}
