package careerfestival.career.eventPage.controller;

import careerfestival.career.comments.Service.CommentService;
import careerfestival.career.comments.dto.CommentResponseDto;
import careerfestival.career.eventPage.dto.EventPageResponseDto;
import careerfestival.career.eventPage.service.EventPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor

public class EventPageController {
    private final EventPageService eventPageService;
    private final CommentService commentService;
    @GetMapping("/event/{eventId}")
        public ResponseEntity<Map<String, Object>> getAllCommentsByEvent(
                @PathVariable("eventId") Long eventId,
                @RequestParam(value = "page", defaultValue = "1") int page,
                @RequestParam(value = "size", defaultValue = "3") int size) {
        int pageSize = size;
        int offset = (page - 1) * pageSize;
            // 페이징 정보를 생성합니다.
            // 댓글을 조회합니다.
            try {
                List<EventPageResponseDto> eventInformation = eventPageService.getEvents(eventId);

                Page<CommentResponseDto> comments = commentService.getAllCommentsByEvent(eventId, pageSize, offset);


                // 댓글 정보를 응답으로 반환합니다.
                Map<String, Object> eventPage = new HashMap<>();
                eventPage.put("eventInformation", eventInformation);
                eventPage.put("comments", comments);
                    return ResponseEntity.ok()
                            .header("Location", "/event/" + eventId + "?page=" + (page + 1))
                            .body(eventPage);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                // Log the exception or return a more specific error response
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

