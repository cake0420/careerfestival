package careerfestival.career.user.controller;

//import careerfestival.career.global.config.JwtTokenProvider;
import careerfestival.career.user.dto.request.LoginDto;
import careerfestival.career.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
//    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("loninId")
    public ResponseEntity<Void> checkLoginId(@RequestBody LoginDto loginDto) {
        return userService.checkLoginId(loginDto);
    }
}
