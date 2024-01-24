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

    public Long commentSave(Long userId, Long eventId, CommentRequestDto commentRequestDto) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);

        if (user.isPresent() && event.isPresent() && commentRequestDto.getCommentContent() != null) {
            Comment comment = commentRequestDto.toEntity(user.orElse(null), event.orElse(null), commentRequestDto.getCommentContent());

            if (commentRequestDto.getParent() != null) {
                // 대댓글인 경우
                Optional<Comment> parentComment = commentRepository.findByOrderNumber(commentRequestDto.getParent());

                if (parentComment.isPresent()) {
                    comment.setOrderNumber(parentComment.get().getOrderNumber());
                    comment.setDepth(parentComment.get().getDepth() + 1);
                } else {
                    // 부모 댓글이 없는 경우에는 예외 처리 또는 기본값 지정
                    return null;
                }
            } else {
                // 댓글인 경우
                int maxOrderNumber = commentRepository.findMaxOrderNumber();
                comment.setOrderNumber((long) (maxOrderNumber + 1));
                comment.setDepth(0);
            }

            Comment savedComment = commentRepository.save(comment);

            return savedComment.getId();
        } else {
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


