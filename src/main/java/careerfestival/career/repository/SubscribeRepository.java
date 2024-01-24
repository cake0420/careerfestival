package careerfestival.career.repository;

import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    List<Subscribe> findByUserId(Long userId);

}
