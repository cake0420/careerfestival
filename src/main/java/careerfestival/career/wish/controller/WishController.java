package careerfestival.career.wish.controller;

import careerfestival.career.jwt.JWTUtil;
import careerfestival.career.login.dto.CustomUserDetails;
import careerfestival.career.wish.dto.WishRequestDto;
import careerfestival.career.wish.dto.WishResponseDto;
import careerfestival.career.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class WishController {
    private final WishService wishService;
    private final JWTUtil jwtUtil;
    @PostMapping("/event/{eventId}/wish")
    public ResponseEntity<Long> addWish(
            @RequestHeader(name = "Authorization") String token, // Assuming the token is passed in the Authorization header
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable("eventId") Long eventId,
            @RequestBody WishRequestDto wishRequestDto) {
        // Assuming you have the authenticated user's email
        String userEmail = "user@example.com"; // Replace this with the actual email
        Long userId = customUserDetails.getId();

        try {
            boolean wishId = wishService.CheckWish(userId, eventId, wishRequestDto);
            // 리다이렉트를 위한 URL 생성
            String redirectUrl = "/event/" + eventId;

            // ResponseEntity로 리다이렉트 응답 생성
            return ResponseEntity.status(HttpStatus.FOUND)
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
    @GetMapping("/event/{eventId}/wish")
    public ResponseEntity<List<WishResponseDto>> getAllWishByEvent(
            @RequestHeader(name = "Authorization") String token, // Assuming the token is passed in the Authorization header
            @PathVariable("eventId") Long eventId) {
        String userEmail = "user@example.com"; // Replace this with the actual email
        String name = "test";
        Long userId = jwtUtil.getUserId(token);


        try {
            List<WishResponseDto> wishlist = wishService.getAllWishByEvent(userId, eventId);
            return new ResponseEntity<>(wishlist, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log the exception or return a more specific error response
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
