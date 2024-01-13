package careerfestival.career.login.api;

import careerfestival.career.login.dto.UserSignRoleRequestDto;
import careerfestival.career.login.dto.UserSignUpRequestDto;
import careerfestival.career.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 1
    @PostMapping("/join")
    public String signUp(UserSignUpRequestDto userSignUpRequestDto) {
        System.out.println("userSignUpRequestDto.getEmail() = " + userSignUpRequestDto.getEmail());
        System.out.println("userSignUpRequestDto.getPassword() = " + userSignUpRequestDto.getPassword());
        userService.signUp(userSignUpRequestDto);
        return "200";

        // 바로 회원가입 2로 넘어가야 함
    }


    @PostMapping("/signrole/{user_id}")
    public String signRole(@PathVariable("user_id") Long user_id, UserSignRoleRequestDto userSignRoleRequestDto) {
        System.out.println("-----------------------------------------------");
        userService.signRole(user_id, userSignRoleRequestDto);
        return "200";
    }

    @GetMapping("/login")
    public String signIn(){
        return "login";
    }

}
