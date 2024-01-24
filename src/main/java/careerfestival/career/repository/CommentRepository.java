package careerfestival.career.repository;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.mapping.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentByCommentContent(String contents);

    Optional<Comment> findByOrderNumberAndEvent(Long orderNumber, Event event);

    List<Comment> findByEventAndParentOrderByOrderNumber(Event event, Comment parent);
    Optional<Comment> findByOrderNumber(Long orderNumber);
    @Query("SELECT COALESCE(MAX(c.orderNumber), 0) FROM Comment c WHERE c.parent IS NULL")
    Integer findMaxOrderNumber();


    @Query("SELECT COALESCE(MAX(c.orderNumber), 0) FROM Comment c WHERE c.parent = :parent")
    Integer findMaxOrderNumberByParent(@Param("parent") Comment parent);


}