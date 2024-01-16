package careerfestival.career.repository;

import careerfestival.career.domain.Comment;
import careerfestival.career.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByEvent_EventName(String eventName);
    List<Comment> findCommentByCommentContent(String contents);
}