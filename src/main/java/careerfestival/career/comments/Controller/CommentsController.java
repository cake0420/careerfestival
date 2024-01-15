package careerfestival.career.comments.Controller;

import careerfestival.career.comments.Service.CommentService;
import careerfestival.career.comments.dto.CommentRequestDto;
import careerfestival.career.comments.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @PostMapping("/event/{id}/comments")
    public ResponseEntity<Long> addComment(
            @PathVariable("id") Long eventId,
            @RequestBody CommentRequestDto commentRequestDto) {
        // Assuming you have the authenticated user's email
        String userEmail = "user@example.com"; // Replace this with the actual email

        try {
            Long commentId = commentService.commentSave(userEmail, String.valueOf(eventId), commentRequestDto);
            return new ResponseEntity<>(commentId, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log the exception or return a more specific error response
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/event/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByEvent(@PathVariable("id") Long eventId) {
        try {
            List<CommentResponseDto> comments = commentService.getAllCommentsByEvent(String.valueOf(eventId));
            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log the exception or return a more specific error response
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
