package careerfestival.career.record.service;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.record.dto.RecordEtcDto;
import careerfestival.career.record.dto.RecordLectureSeminarDto;
import careerfestival.career.record.dto.RecordMainResponseDto;
import careerfestival.career.repository.RecordKeywordRepository;
import careerfestival.career.repository.RecordRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final RecordKeywordRepository recordKeywordRepository;
    private final UserRepository userRepository;

    public void recordLectureSeminar(Long userId, RecordLectureSeminarDto recordLectureSeminarDto) {

        // 전달받은 userId로 연관관계 매핑된 User 테이블에 맞춰서 DB에 저장하기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Record record = recordLectureSeminarDto.toEntity();
        record.setUser(user);

        recordRepository.save(record);
    }



    public void recordEtc(Long userId, RecordEtcDto recordEtcDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found with id: " + userId));

        Record record = recordEtcDto.toEntity();
        record.setUser(user);
        recordRepository.save(record);
        recordKeywordRepository.saveAll(record.getRecordKeywords());
        // Record 테이블에만 저장된 상태
    }

    public List<RecordMainResponseDto> getRecordsByUserId(Long userId) {
        List<Record> records = recordRepository.findByUserId(userId);
        return records.stream()
                .map(RecordMainResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
