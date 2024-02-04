package careerfestival.career.myPage.api;

import careerfestival.career.domain.User;
import careerfestival.career.login.dto.CustomUserDetails;
import careerfestival.career.myPage.dto.MyPageEventResponseDto;
import careerfestival.career.myPage.dto.MyPageUserInfoResponseDto;
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

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final UserService userService;
    private final MyPageService myPageService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> myPage (@AuthenticationPrincipal CustomUserDetails customUserDetails){
        try{
            //사용자 정보
            User findUser = userService.findUserByCustomUserDetails(customUserDetails);
            MyPageUserInfoResponseDto myPageUserInfoResponse = userService.fillMyPage(findUser);

            //내가 관심있는 행사
            List<MyPageEventResponseDto> wishEvent = myPageService.getWishEvent(findUser);

            //내가 참가 확정한 행사
            List<MyPageEventResponseDto> participateEvent = myPageService.getParticipateEvent(findUser);

            //내가 주최한 행사 (아직 구현x)

            Map<String, Object> myPageResponeDtoObjectMap = new HashMap<>();
            myPageResponeDtoObjectMap.put("userInfo", myPageUserInfoResponse);
            myPageResponeDtoObjectMap.put("wishEvent", wishEvent);
            myPageResponeDtoObjectMap.put("participateEvent", participateEvent);

            return ResponseEntity.ok().body(myPageResponeDtoObjectMap);

        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> myPageUpdate (@AuthenticationPrincipal CustomUserDetails customUserDetails){
        try{
            //사용자 정보
            User findUser = userService.findUserByCustomUserDetails(customUserDetails);
            MyPageUserInfoResponseDto myPageUserInfoResponse = userService.fillMyPage(findUser);

            //내가 관심있는 행사
            List<MyPageEventResponseDto> wishEvent = myPageService.getWishEvent(findUser);

            //내가 참가 확정한 행사
            List<MyPageEventResponseDto> participateEvent = myPageService.getParticipateEvent(findUser);

            //내가 주최한 행사 (아직 구현x)

            Map<String, Object> myPageResponeDtoObjectMap = new HashMap<>();
            myPageResponeDtoObjectMap.put("userInfo", myPageUserInfoResponse);
            myPageResponeDtoObjectMap.put("wishEvent", wishEvent);
            myPageResponeDtoObjectMap.put("participateEvent", participateEvent);

            return ResponseEntity.ok().body(myPageResponeDtoObjectMap);

        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PatchMapping("/update")
    public ResponseEntity<Void> updateMember(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody UpdateMypageResponseDto updateMypageResponseDto) {

        User findUser = userService.findUserByCustomUserDetails(customUserDetails);
        userService.findUserByEmailAndUpdate(findUser.getEmail(), updateMypageResponseDto);

        // 회원정보 수정 이후 리다이렉션할 URL 생성
        String redirectUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/mypage")
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", redirectUrl);

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
    }
}

