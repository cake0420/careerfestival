package careerfestival.career.repository;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.enums.Category;
import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.domain.mapping.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    boolean existsByEventName(String eventName);
    Event findByEventName(String eventName);

    /*
     사용자가 등록한 행사 리스트 조회하기 위함.
     5단계의 행사 목록 List 형태로 제시
     */

    List<Event> findAllByUserId(Long userId);

    Event findByUserId(Long userId);
    @Query("SELECT e FROM Event e ORDER BY e.hits DESC")
    List<Event> findAllByOrderByHitsDesc();
    @Query(value = "SELECT * FROM Event ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<Event> findRandomEvents(int limit);
    @Query(value = "SELECT e FROM Event e WHERE e.category IN (?1) AND e.keywordName IN (?2) AND e.region.id = ?3")
    Page<Event> findAllByCategoryKeywordName(List<Category> category,
                                             List<KeywordName> keywordName,
                                             Long regionId,
                                             Pageable pageable);
    Page<Event> findPageByOrganizerId(Long organizerId, Pageable pageable);
    Event findByOrganizerId(Long organizerId);
    @Query("SELECT COUNT(e) FROM Event e WHERE e.organizer.id = ?1")
    int countEventsByOrganizerId(Long organizerId);
}