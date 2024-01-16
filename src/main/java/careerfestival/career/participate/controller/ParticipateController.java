package careerfestival.career.participate.controller;

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

    @PostMapping("/event/{userId}/{eventId}/participate")
    public ResponseEntity<Long> addParticipate(
            @PathVariable("userId") Long userId,
            @PathVariable("eventId") Long eventId,
            @RequestBody ParticipateRequestDto participateRequestDto) {
        // Assuming you have the authenticated user's email
        String userEmail = "user@example.com"; // Replace this with the actual email
        String eventName = "test";
        try {
            Long participateId = participateService.participateSave(userId, eventId, participateRequestDto);
            return new ResponseEntity<>(participateId, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Log the exception or return a more specific error response
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
        @GetMapping("/event/{userId}/{eventId}/participate")
        public ResponseEntity<List<ParticipateResponseDto>> getAllCommentsByEvent(
                @PathVariable("userId") Long userId,
                @PathVariable("eventId") Long eventId) {
            String userEmail = "user@example.com"; // Replace this with the actual email
            String name = "test";
            String nam = "test";
            try {
                List<ParticipateResponseDto> comments = participateService.getAllParticipateByEvent(userId, eventId, nam);
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
