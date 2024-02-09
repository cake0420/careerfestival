package careerfestival.career.repository;

import careerfestival.career.domain.enums.Category;
import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.domain.mapping.Organizer;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    Organizer findByUserId(Long userId);

    Organizer findByEncryptedEmail(String encryptedEmail);

    // 주최자가 주최한 행사 중에서 category와 keywordName에 의한 필터링이 적용된 상태의 주최자들을 보여줘야 함

    @Query("SELECT o FROM Organizer o JOIN o.event e WHERE e.category = :category AND e.keywordName = :keywordName")
    Page<Organizer> findAllByCategoryKeywordName(@Param("category") Category category, @Param("keywordName") KeywordName keywordName, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT organizer_name FROM organizer WHERE id=?1")
    String findOrganizerNameByOrganizerId(Long organizerId);

    @Query("SELECT COUNT(o) FROM Organizer o")
    int countOrganizer();
    @Query("SELECT o FROM Organizer o")
    Page<Organizer> findOrganizers(Pageable organizerPageable);
}