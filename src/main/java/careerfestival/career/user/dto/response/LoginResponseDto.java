package careerfestival.career.user.dto.response;


import careerfestival.career.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginResponseDto {
    private String name;
    private String accessToken;

    public LoginResponseDto(User user, String accessToken) {
        this.name = user.getName();
        this.accessToken = accessToken;
    }
}
