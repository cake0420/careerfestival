package careerfestival.career.comments.Service;

import careerfestival.career.comments.dto.CommentRequestDto;
import careerfestival.career.comments.dto.CommentResponseDto;
import careerfestival.career.domain.mapping.Comment;
import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Comment;
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

    public Long commentSave(Long userId, Long eventId, CommentRequestDto commentRequestDto)
    {
        Optional<User> user = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);
        if (user.isPresent() && event.isPresent() && commentRequestDto.getCommentContent() != null) {
//            int depth = (commentRequestDto.getParentCommentId() != null) ? parentComment.getDepth() + 1 : 0;
            Comment comment = commentRequestDto.toEntity(user.get(), event.get(), commentRequestDto.getCommentContent());
//            comment.setDepth(depth);

//            Comment comments = commentRequestDto.toEntity(user.orElse(null), event.orElse(null), )
            Comment savedComment = commentRepository.save(comment);
            return savedComment.getId();
        } else {
            // Handle the case when user or event is not found
            return null;
        }
    }
    public List<CommentResponseDto> getAllCommentsByEvent(String comment, Long userId, Long eventId) {
        List<Comment> comments = commentRepository.findCommentByCommentContent(comment);
        Optional<User> userid = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

    }
}
