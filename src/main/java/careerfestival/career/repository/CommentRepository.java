package careerfestival.career.repository;

import careerfestival.career.domain.mapping.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByEventIdOrderByCommentContentDesc(Long event_id, Pageable pageable);

    List<Comment> findByOrderNumber(Long orderNumber);

    @Query("SELECT COALESCE(MAX(c.orderNumber), 0) FROM Comment c WHERE c.parent IS NULL")
    Long findMaxOrderNumber();
    

}