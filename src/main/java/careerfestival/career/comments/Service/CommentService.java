package careerfestival.career.comments.Service;

import careerfestival.career.comments.dto.CommentRequestDto;
import careerfestival.career.comments.dto.CommentResponseDto;
import careerfestival.career.domain.mapping.Comment;
import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.repository.CommentRepository;
import careerfestival.career.repository.EventRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public Long commentSave(String email, Long userId, Long eventId, CommentRequestDto commentRequestDto)
    {
        User user = userRepository.findByEmail(email);
        Optional<User> userid = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);

        Comment comment = commentRequestDto.toEntity();
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }
    public List<CommentResponseDto> getAllCommentsByEvent(String comment, String email, Long userId, Long eventId) {
        List<Comment> comments = commentRepository.findCommentByCommentContent(comment);
        User user = userRepository.findByEmail(email);
        Optional<User> userid = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

    }
}
