package careerfestival.career.repository;

import careerfestival.career.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
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

    List<Event> findByUserId(Long userId);
}