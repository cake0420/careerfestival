package careerfestival.career.jeongyeon.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERID")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;




}
