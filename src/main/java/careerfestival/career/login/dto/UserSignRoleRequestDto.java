package careerfestival.career.login.dto;

import careerfestival.career.domain.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignRoleRequestDto {
    private Role role;
}
