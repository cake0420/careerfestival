package careerfestival.career.comments.Controller;

import careerfestival.career.comments.Service.CommentService;
import careerfestival.career.comments.dto.CommentRequestDto;
import careerfestival.career.domain.User;
import careerfestival.career.login.dto.CustomUserDetails;
import careerfestival.career.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class CommentsController {
    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/event/{eventId}/comments")
    public ResponseEntity<Long> addComment(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestHeader(name = "Authorization") String token,
            @RequestBody CommentRequestDto commentRequestDto,
            @PathVariable("eventId") Long eventId){
        // Assuming you have the authenticated user's email
        // Replace this with the actual email

        User findUser = userService.findUserByCustomUserDetails(customUserDetails);
        Long userId = findUser.getId();
        try {
            Long commentId = commentService.commentSave(userId, eventId, commentRequestDto);
            // 리다이렉트를 위한 URL 생성
            String redirectUrl = "/event/" + eventId;

            // ResponseEntity로 리다이렉트 응답 생성
            return ResponseEntity.ok()
                    .header("Location", redirectUrl)
                    .body(commentId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log the exception or return a more specific error response
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
