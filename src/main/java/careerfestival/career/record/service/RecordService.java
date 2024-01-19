package careerfestival.career.record.service;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.record.dto.RecordEtcDto;
import careerfestival.career.record.dto.RecordLectureSeminarDto;
import careerfestival.career.record.dto.RecordMainResponseDto;
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
    private final UserRepository userRepository;

    public void recordLectureSeminar(Long userId, RecordLectureSeminarDto recordLectureSeminarDto) {
        // 이미지 첨부 및 글자 수 제한 적용 필요
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Record record = recordLectureSeminarDto.toEntity();
        record.setCategory(recordLectureSeminarDto.getCategory());
        record.setKeywordName(recordLectureSeminarDto.getKeywordName());
        record.setUser(user);
        recordRepository.save(record);
    }

    public void recordEtc(Long userId, RecordEtcDto recordEtcDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found with id: " + userId));

        Record record = recordEtcDto.toEntity();
        record.setCategory(recordEtcDto.getCategory());
        record.setKeywordName(recordEtcDto.getKeywordName());
        record.setUser(user);
        recordRepository.save(record);
    }

    public List<RecordMainResponseDto> getRecordsByUserId(Long userId) {
        List<Record> records = recordRepository.findByUserId(userId);

        return records.stream()
                .map(RecordMainResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
