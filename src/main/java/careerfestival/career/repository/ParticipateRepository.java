package careerfestival.career.repository;

import careerfestival.career.domain.mapping.Participate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipateRepository extends JpaRepository<Participate, Long> {
    List<Participate> findByEvent_EventName(String eventName);

}
