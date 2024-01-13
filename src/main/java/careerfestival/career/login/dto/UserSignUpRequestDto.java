package careerfestival.career.login.dto;

import lombok.*;

@Getter
@Setter
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String name;
}
