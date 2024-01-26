package careerfestival.career.record.service;

import careerfestival.career.domain.User;
import careerfestival.career.domain.Record;
import careerfestival.career.record.dto.RecordRequestDto;
import careerfestival.career.repository.RecordRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RecordConferenceExhibitionService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;


    public void recordConference(Long userId, RecordRequestDto request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Record record = request.toEntity();
        record.setUser(user);
        recordRepository.save(record);
        // Record 테이블에만 저장된 상태
    }

    }

