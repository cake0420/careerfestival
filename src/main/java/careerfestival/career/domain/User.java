package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.enums.UserStatus;
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
public class User extends BaseEntity {

    // 회원가입 1 화면
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300, name = "email")
    private String email;

    @Column(nullable = false, length = 300, name = "password")
    private String password;

    @Column(nullable = false, length = 20, name = "name")
    private String name;

    @Column(nullable = false, length = 200, name = "phone_number")
    private String phoneNumber;

    // status와 inactivedate는 회원 탈퇴, 게시글 삭제 시 필요 기능
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus userStatus;

    // 회원가입 2 화면
    @Enumerated(EnumType.STRING)
    private Role role;

    // 회원가입 3 화면 (대략 6가지)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition = "INT")
    private int age;

    // 회사
    @Column(length = 20, name = "company")
    private String company;

    // ???
    @Column(length = 20, name = "position")
    private String position;

    @OneToMany(mappedBy = "user")
    private List<Record> records = new ArrayList<>();
}