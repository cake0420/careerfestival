package careerfestival.career.subscribe.controller;


import careerfestival.career.AES.AESUtil;
import careerfestival.career.jwt.JWTUtil;
import careerfestival.career.login.dto.CustomUserDetails;
import careerfestival.career.login.service.UserService;
import careerfestival.career.register.dto.RegisterMainResponseDto;
import careerfestival.career.register.service.RegisterService;
import careerfestival.career.repository.OrganizerRepository;
import careerfestival.career.repository.SubscribeRepository;
import careerfestival.career.repository.UserRepository;
import careerfestival.career.subscribe.dto.SubscribeRequestDto;
import careerfestival.career.subscribe.dto.SubscribeResponseDto;
import careerfestival.career.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscribeService subscribeService;
    private final RegisterService registerService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final SubscribeRepository subscribeRepository;
    private final OrganizerRepository organizerRepository;

    // 구독하기
    @PostMapping("/profile/{fromUserId}/subs")
    public ResponseEntity<Long> addSubs(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable String fromUserId,
            @RequestBody SubscribeRequestDto subscribeRequestDto) {
        if(customUserDetails == null)
        {
            return ResponseEntity.ok()
                    .header("Location", "/login")
                    .build();

        }
        Long findUser = userService.findUserByCustomUserDetails(customUserDetails).getId();
        try {
            boolean subsId = subscribeService.addRemove(findUser, subscribeRequestDto);
            String redirectUrl = "/profile/" + fromUserId;
            return ResponseEntity.ok()
                    .header("Location", redirectUrl)
                    .build();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log the exception or return a more specific error response
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/profile/{fromUserId}/subs")
    public ResponseEntity<List<SubscribeResponseDto>> getAllSubscribeByUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("fromUserId") Long fromUserId) {


        String toUser = customUserDetails.getUsername();
        Long toUserId = userRepository.findByEmail(toUser).getId();
        try {
            List<SubscribeResponseDto> subs = subscribeService.getAllSubscribeByUser(toUserId);
            return new ResponseEntity<>(subs, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("profile/{fromUserId}")
    public ResponseEntity<Map<String, Object>> getRegisterByOrganizerId(
            @PathVariable String fromUserId,
            @PageableDefault(size = 4, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        try{

            Long organizerId = organizerRepository.findByEncryptedEmail(fromUserId).getId();
            Long organizerUserId = organizerRepository.findByEncryptedEmail(fromUserId).getUser().getId();
            System.out.println(organizerId + "주최자 id");
            System.out.println(organizerUserId + "주최자의 유저 id");
            String organizerName = registerService.getOrganizerName(organizerId);
            int getEventCount = registerService.getCountRegisterEvent(organizerUserId);
            Page<RegisterMainResponseDto> registerMainResponseDtos
                    = registerService.getEventList(organizerId, pageable);

            List<SubscribeResponseDto> subs = subscribeService.getAllSubscribeByUser(organizerId);
            int totalSubsCount = subs.size();
            Map<String, Object> profile = new HashMap<>();
            profile.put("organizerName", organizerName);
            // 구독자 관련 코드 추가 작성 필요
            profile.put("festivalList", registerMainResponseDtos);
            profile.put("festivalCount", getEventCount);
            profile.put("subsCount", totalSubsCount);

            return ResponseEntity.ok().body(profile);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
