package careerfestival.career.comments.Controller;

import careerfestival.career.comments.Service.CommentService;
import careerfestival.career.comments.dto.CommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class CommentsController {
    private final CommentService commentService;
    @PostMapping("/event/{eventId}/{userId}/comments")
    public ResponseEntity<Long> addComment(
            @RequestBody CommentRequestDto commentRequestDto,
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId){
        // Assuming you have the authenticated user's email
        // Replace this with the actual email

        try {
            Long commentId = commentService.commentSave(userId, eventId, commentRequestDto);
            // 리다이렉트를 위한 URL 생성
            String redirectUrl = "/event/" + eventId + "/" + userId;

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
