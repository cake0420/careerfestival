package careerfestival.career.myPage.api;

import careerfestival.career.domain.User;
import careerfestival.career.login.dto.CustomUserDetails;
import careerfestival.career.myPage.dto.MyPageResponseDto;
import careerfestival.career.myPage.dto.UpdateMypageResponseDto;
import careerfestival.career.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("")
    @ResponseBody
    public MyPageResponseDto myPage (@AuthenticationPrincipal CustomUserDetails customUserDetails){
        User findUser = userService.findUserByCustomUserDetails(customUserDetails);
        return userService.fillMyPage(findUser);
    }


    @PatchMapping("/update")
    public ResponseEntity<Void> updateMember(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody UpdateMypageResponseDto updateMypageResponseDto) {
        userService.findUserByEmailAndUpdate(customUserDetails.getUsername(), updateMypageResponseDto);

        // 회원정보 수정 이후 리다이렉션할 URL 생성
        String redirectUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/mypage")
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", redirectUrl);

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}

