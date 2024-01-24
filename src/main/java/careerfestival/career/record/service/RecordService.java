package careerfestival.career.record.service;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.global.S3Uploader;
import careerfestival.career.record.dto.*;
import careerfestival.career.repository.RecordRepository;
import careerfestival.career.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    @Autowired
    private S3Uploader s3Uploader;

    public void recordLectureSeminar(Long userId, RecordLectureSeminarRequestDto recordLectureSeminarRequestDto) {
        // 이미지 첨부 및 글자 수 제한 적용 필요
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Record record = recordLectureSeminarRequestDto.toEntity();
        record.setUser(user);
        recordRepository.save(record);
    }

    @Transactional
    public void recordLectureSeminarImage(Long userId, MultipartFile lectureseminarimage) throws IOException {
        Record record = recordRepository.findRecordByUserId(userId);
        if(!lectureseminarimage.isEmpty()){
            String storedFileName = s3Uploader.upload(lectureseminarimage, "lectureseminar_image");
            record.setRecordLectureSeminarFileUrl(storedFileName);
        }
        recordRepository.save(record);
    }

    public void recordEtc(Long userId, RecordEtcRequestDto recordEtcRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found with id: " + userId));

        Record record = recordEtcRequestDto.toEntity();
        record.setUser(user);
        recordRepository.save(record);
    }

    public void recordEtcImage(Long userId, MultipartFile etcimage) throws IOException{
        Record record = recordRepository.findRecordByUserId(userId);
        if(!etcimage.isEmpty()){
            String storedFileName = s3Uploader.upload(etcimage, "etc_image");
            record.setRecordEtcFileUrl(storedFileName);
        }
        recordRepository.save(record);
    }

    // 사용자별 기록장 메인페이지 페이징 기법 적용해서 반환 (updated_at) 기준 내림차순 정렬
    public Page<RecordMainResponseDto> recordList(Long userId, Pageable pageable) {
        Page<Record> records = recordRepository.findByUserId(userId, pageable);
        return records.map(RecordMainResponseDto::fromEntity);
    }

    // 수정하기 버튼 누르기 전 사용자별 기록장(강연/세미나) 조회 및 반환
    public RecordLectureSeminarResponseDto getLectureSeminar(Long recordId) {
        Record record = recordRepository.findRecordById(recordId);
        RecordLectureSeminarResponseDto responseDto = RecordLectureSeminarResponseDto.fromEntity(record);
        return responseDto;
    }

    // 수정하기 버튼 누르기 전 사용자별 기록장(기타) 조회 및 반환
    public RecordEtcResponseDto getEtc(Long recordId) {
        Record record = recordRepository.findRecordById(recordId);
        RecordEtcResponseDto responseDto = RecordEtcResponseDto.fromEntity(record);
        return responseDto;
    }

}