package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.enums.Gender;
import careerfestival.career.domain.enums.Role;
import careerfestival.career.domain.enums.UserStatus;
import careerfestival.career.domain.mapping.Participate;
import careerfestival.career.domain.mapping.RecordKeyword;
import careerfestival.career.domain.mapping.UserKeyword;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
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

    @Column(nullable = false, length = 300, name = "email")
    private String email;

    @Column(nullable = false, length = 300, name = "password")
    private String password;

    @Column(nullable = false, length = 20, name = "name")
    private String name;

    @Column(nullable = false, length = 200, name = "phone_number")
    private String phoneNumber;

    // status와 inacticedate는 회원 탈퇴, 게시글 삭제 시 필요 기능
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private UserStatus status;

    private Timestamp inactiveDate;
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

    @Column(length = 20, name = "company")
    private String company;

    @Column(length = 20, name = "position")
    private String position;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Event> event = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserKeyword> userKeyWord = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wish> wish = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Participate> participate = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Host> host = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Follow> follow = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Record> records = new ArrayList<>();


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


    public void addRecord(Record record){
        records.add(record);
    }

}