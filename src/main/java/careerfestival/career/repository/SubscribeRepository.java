package careerfestival.career.repository;
import careerfestival.career.domain.mapping.Organizer;
import careerfestival.career.domain.mapping.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    List<Subscribe> findBySubscribedOrganizerId(Long subscribedOrganizer);

    Subscribe findBySubscribedOrganizer_IdAndToUser_id(Long fromUser_id, Long toUser_id);


    @Query("SELECT COUNT(s) FROM Subscribe s WHERE s.subscribedOrganizer = ?1")
    int findBySubscribedOrganizer(Organizer organizer);

}
