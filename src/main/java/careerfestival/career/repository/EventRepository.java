package careerfestival.career.repository;

import careerfestival.career.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    boolean existsByEventName(String eventName);

    Event findByEventName(String eventName);


}