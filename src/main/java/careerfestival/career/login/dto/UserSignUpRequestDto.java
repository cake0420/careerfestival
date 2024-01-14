package careerfestival.career.login.dto;

import careerfestival.career.domain.User;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String name;

    @Builder
    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
