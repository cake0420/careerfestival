//package careerfestival.career.subscribe.controller;
//
//import careerfestival.career.domain.mapping.Subscribe;
//import careerfestival.career.jwt.JWTUtil;
//import careerfestival.career.login.dto.CustomUserDetails;
//import careerfestival.career.subscribe.dto.SubscribeRequestDto;
//import careerfestival.career.subscribe.dto.SubscribeResponseDto;
//import careerfestival.career.subscribe.service.SubscribeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class SubscribeController {
//    private final JWTUtil jwtUtil;
//    private final SubscribeService subscribeService;
//
//    @PostMapping("/profile/subs")
//    public ResponseEntity<Long> addSubs(
//            @RequestHeader(name = "Authorization") String token, // Assuming the token is passed in the Authorization header
//            @AuthenticationPrincipal CustomUserDetails customUserDetails,
//            @RequestBody SubscribeRequestDto subscribeRequestDto) {
//
//        Long userId = customUserDetails.getId();
//
//        try {
//            boolean subsId = subscribeService.addRemove(userId, subscribeRequestDto);
//            String redirectUrl = "/profile";
//            return ResponseEntity.status(HttpStatus.FOUND)
//                    .header("Location", redirectUrl)
//                    .build();
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            // Log the exception or return a more specific error response
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//    @GetMapping("/profile/subs")
//    public ResponseEntity<List<Long>> getAllSubscribeByUser(
//            @RequestHeader(name = "Authorization") String token) {
//
//        Long userId = jwtUtil.getUserId(token);
//        try {
//            List<Long> subs = subscribeService.getAllSubscribeByUser(userId);
//            return new ResponseEntity<>(subs, HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
