package careerfestival.career.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User {

    // 회원가입 1 화면
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String email;
    private String password;
    private String name;

    // 회원가입 2 화면
    @Enumerated(EnumType.STRING)
    private Role role;

    // 회원가입 3 화면 (대략 6가지)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<InterestRegion> interestRegion = new ArrayList<>(); -> 관심 지역

    private String phoneNumber;

    // 소속(회사/기관/학교명)
    // 커리어 키워드
}