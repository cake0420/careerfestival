package careerfestival.career.record.service;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.record.dto.RecordEtcRequestDto;
import careerfestival.career.record.dto.RecordLectureSeminarRequestDto;
import careerfestival.career.record.dto.RecordMainResponseDto;
import careerfestival.career.repository.RecordRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    public void recordLectureSeminar(Long userId, RecordLectureSeminarRequestDto recordLectureSeminarRequestDto) {
        // 이미지 첨부 및 글자 수 제한 적용 필요
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Record record = recordLectureSeminarRequestDto.toEntity();
        record.setUser(user);
        recordRepository.save(record);
    }

    public void recordEtc(Long userId, RecordEtcRequestDto recordEtcRequestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found with id: " + userId));

        Record record = recordEtcRequestDto.toEntity();
        record.setUser(user);
        recordRepository.save(record);
    }

    public Page<RecordMainResponseDto> recordList(Long userId, Pageable pageable) {
        Page<Record> records = recordRepository.findByUserId(userId, pageable);
        return records.map(RecordMainResponseDto::fromEntity);
    }
}