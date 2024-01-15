package careerfestival.career.participate.controller;

import careerfestival.career.comments.dto.CommentRequestDto;
import careerfestival.career.comments.dto.CommentResponseDto;
import careerfestival.career.domain.mapping.Participate;
import careerfestival.career.participate.dto.ParticipateRequestDto;
import careerfestival.career.participate.dto.ParticipateResponseDto;
import careerfestival.career.participate.service.ParticipateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipateController {
    private final ParticipateService participateService;

    @PostMapping("/event/{id}/participate")
    public ResponseEntity<Long> addParticipate(
            @PathVariable("id") Long eventId,
            @RequestBody ParticipateRequestDto participateRequestDto) {
        // Assuming you have the authenticated user's email
        String userEmail = "user@example.com"; // Replace this with the actual email

        try {
            Long participateId = participateService.participateSave(userEmail, String.valueOf(eventId), participateRequestDto);
            return new ResponseEntity<>(participateId, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log the exception or return a more specific error response
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        @GetMapping("/event/{id}/participate")
        public ResponseEntity<List<ParticipateResponseDto>> getAllCommentsByEvent(@PathVariable("id") Long eventId) {
            try {
                List<ParticipateResponseDto> comments = participateService.getAllParticipateByEvent(String.valueOf(eventId));
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
