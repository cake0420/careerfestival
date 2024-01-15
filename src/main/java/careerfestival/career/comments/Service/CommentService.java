package careerfestival.career.comments.Service;

import careerfestival.career.comments.dto.CommentRequestDto;
import careerfestival.career.comments.dto.CommentResponseDto;
import careerfestival.career.domain.Comment;
import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.repository.CommentRepository;
import careerfestival.career.repository.EventRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public Long commentSave(String email, String eventName, CommentRequestDto commentRequestDto)
    {
        User user = userRepository.findByEmail(email);
        Event event = eventRepository.findByEventName(eventName);

        Comment comment = commentRequestDto.toEntity();
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }
    public List<CommentResponseDto> getAllCommentsByEvent(String eventName) {
        List<Comment> comments = commentRepository.findByEvent_EventName(eventName);
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

    }
}
