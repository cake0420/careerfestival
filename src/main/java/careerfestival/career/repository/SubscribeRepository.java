package careerfestival.career.repository;

import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Organizer;
import careerfestival.career.domain.mapping.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    List<Subscribe> findByFromUserId(Long fromUser);

    Subscribe findByFromUser_IdAndToUser_Id(Long formUserId, Long userId);

    @Query("SELECT COUNT(s) FROM Subscribe s WHERE s.fromUser = ?1")
    int findAllByFromUser_Id(Long formUserId);

    @Query("SELECT COUNT(s) FROM Subscribe s WHERE s.fromUser = ?1")
    int findByFromUser(Organizer fromUser);

}
