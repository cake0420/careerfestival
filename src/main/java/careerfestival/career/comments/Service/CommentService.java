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
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageImpl;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
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
                if (commentRequestDto.getParent() != null) {
                    // 대댓글인 경우
                    List<Comment> parentComment = commentRepository.findByOrderNumber(commentRequestDto.getParent());

                    if (!parentComment.isEmpty()) {
                        Comment lastParentComment = parentComment.get(parentComment.size() - 1); // 마지막 부모 댓글을 가져옴
                        Comment comment = commentRequestDto.toEntityWithParent(user.orElse(null), event.orElse(null), commentRequestDto.getCommentContent(), lastParentComment);

                        // 대댓글이므로 isParent를 false로 설정
                        comment.setIsParent(false);
                        comment.setOrderNumber(lastParentComment.getOrderNumber());
                        comment.setDepth(lastParentComment.getDepth() + 1);

                        Comment savedComment = commentRepository.save(comment);
                        return savedComment.getId();
                    } else {
                        // 부모 댓글이 없는 경우에는 예외 처리 또는 기본값 지정
                        return null;
                    }
                } else {
                    // 댓글인 경우
                    Comment comment = commentRequestDto.toEntity(user.orElse(null), event.orElse(null), commentRequestDto.getCommentContent());

                    // 기존 코드에서의 위치 변경 및 isParent 값 설정
                    comment.setIsParent(true); // 댓글이므로 isParent를 true로 설정
                    comment.setOrderNumber( (commentRepository.findMaxOrderNumber() + 1));
                    comment.setDepth(0);

                    Comment savedComment = commentRepository.save(comment);
                    return savedComment.getId();
                }
            } else {
                return null;
            }
        }

        public Page<CommentResponseDto> getAllCommentsByEvent(Long eventId, int pageSize, int offset) {
            List<Comment> comments = commentRepository.findAllLimitedParentCommentsWithRepliesByEventId(eventId, pageSize, offset);

            Page<CommentResponseDto> page = new PageImpl<>(
                    comments.stream()
                            .map(CommentResponseDto::new)
                            .collect(Collectors.toList()),
                    PageRequest.of(offset, pageSize),
                    comments.size()
            );

            return page;        }

    }


