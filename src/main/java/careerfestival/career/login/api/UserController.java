package careerfestival.career.login.api;

import careerfestival.career.domain.Role;
import careerfestival.career.login.dto.UserSignDetailRequestDto;
import careerfestival.career.login.dto.UserSignInRequestDto;
import careerfestival.career.login.dto.UserSignRoleRequestDto;
import careerfestival.career.login.dto.UserSignUpRequestDto;
import careerfestival.career.login.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 1
    @PostMapping("/join")
    public ResponseEntity<Void> signUp(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        Long userId = userService.signUp(userSignUpRequestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/join/role?userId=" + userId);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/join/role")
    public ResponseEntity<Void> signRole(@RequestParam("userId") Long userId, @RequestBody UserSignRoleRequestDto userSignRoleRequestDto) {
        try {
            userService.signRole(userId, userSignRoleRequestDto);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/join/detail?userId=" + userId);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/join/detail")
    public ResponseEntity<Void> signDetail(@RequestParam("userId") Long userId, @RequestBody UserSignDetailRequestDto userSignDetailRequestDto) {
        try {
            userService.signDetail(userId, userSignDetailRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/login")
////    @ResponseStatus(HttpStatus.OK)
////    public ResponseEntity login(@Valid @RequestBody UserSignInRequestDto userSignInRequestDto) throws Exception {
////        return new ResponseEntity<>(userService.signIn(userSignInRequestDto), HttpStatus.OK);
////    }
}
