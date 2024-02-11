package careerfestival.career.repository;


import careerfestival.career.domain.mapping.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByOrderNumber(Long orderNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Inquiry i SET i.checked = true WHERE i.isParent = true AND i.orderNumber = :orderNumber AND i.event.id = :eventId")
    void updateCheckStatusBeforeOrderNumber(@Param("orderNumber") Long orderNumber, @Param("eventId") Long eventId);

    @Query("SELECT COALESCE(MAX(c.orderNumber), 0) FROM Inquiry c WHERE c.parent IS NULL")
    Long findMaxOrderNumber();

    @Query(value = "(SELECT c.* " +
            "FROM Inquiry c " +
            "WHERE c.event_id = :eventId " +
            "AND c.is_parent = 1 " +
            "ORDER BY c.order_number DESC , c.created_at DESC " +
            "LIMIT :pageStandard OFFSET :setOff) " +
            "UNION ALL " +
            "(SELECT c.* " +
            "FROM Inquiry c " +
            "WHERE c.event_id = :eventId " +
            "AND c.is_parent = 0 " +
            "AND c.order_number IN ( " +
            "    SELECT c.id " +
            "    FROM Inquiry c " +
            "    WHERE c.event_id = :eventId " +
            "    AND c.is_parent = 1 " +
            "ORDER BY order_number ASC, created_at ASC" +
            ")) " +
            "ORDER BY order_number DESC ", // 수정된 부분
            nativeQuery = true)
    List<Inquiry> findAllLimitedParentCommentsWithRepliesByEventId(
            @Param("eventId") Long eventId,
            @Param("pageStandard") int pageStandard,
            @Param("setOff") int setOff);

}
