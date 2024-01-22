package careerfestival.career.repository;

import careerfestival.career.domain.mapping.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByCommentContent(String contents);
    List<Comment> findCommentByParentComment(Comment parentComment);


}