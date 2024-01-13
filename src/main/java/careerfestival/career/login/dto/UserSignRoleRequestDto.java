package careerfestival.career.login.dto;

import careerfestival.career.domain.Role;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class UserSignRoleRequestDto {
    private Role role;
}