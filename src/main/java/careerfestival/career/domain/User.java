package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User extends BaseEntity {

    // 회원가입 1 화면
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Email
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

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateGender(Gender gender) {
        this.gender = gender;
    }

    public void updateAge(int age) {
        this.age = age;
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    // 회사
    @Column(length = 20, name = "company")
    private String company;


    // ???
    @Column(length = 20, name = "position")
    private String position;

    @OneToMany(mappedBy = "user")
    private List<Record> records = new ArrayList<>();
}