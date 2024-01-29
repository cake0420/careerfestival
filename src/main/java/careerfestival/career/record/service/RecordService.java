package careerfestival.career.record.service;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.RecordDetail;
import careerfestival.career.global.ImageUtils;
import careerfestival.career.global.S3Uploader;
import careerfestival.career.record.dto.*;
import careerfestival.career.repository.RecordRepository;
import careerfestival.career.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


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

        if (recordLectureSeminarRequestDto.getRecordDetails() != null) {
            for (RecordDetail detail : recordLectureSeminarRequestDto.getRecordDetails()) {
                detail.setRecord(record); // RecordDetail에 Record 설정
            }
        }
        recordRepository.save(record);
    }

    // 강연 및 세미나 이미지 라시이징 업로드 -> 과연 필요한가?
    @Transactional
    public void recordLectureSeminarImage(Long userId, MultipartFile lectureSeminarImage) throws IOException {
        Record record = recordRepository.findRecordByUserId(userId);
        try {
            if (!lectureSeminarImage.isEmpty()) {
                // 이미지 리사이징
                BufferedImage resizedImage = ImageUtils.resizeImage(lectureSeminarImage, 600, 400);

                // BufferedImage를 byte[]로 변환
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(resizedImage, getFileExtension(lectureSeminarImage.getOriginalFilename()), baos);
                byte[] resizedImageBytes = baos.toByteArray();

                // byte[]를 MultipartFile로 변환
                MultipartFile multipartFile = new MockMultipartFile(
                        "resized_" + lectureSeminarImage.getOriginalFilename(),
                        lectureSeminarImage.getOriginalFilename(),
                        lectureSeminarImage.getContentType(),
                        resizedImageBytes
                );

                // S3에 업로드하고 URL 받기
                String storedFileName = s3Uploader.upload(multipartFile, "lecture_seminar_image");

                // 이벤트에 이미지 URL 설정하고 저장
                record.setRecordLectureSeminarFileUrl(storedFileName);
                recordRepository.save(record);
                }
            } catch (Exception e){
                e.printStackTrace();
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

    public void recordEtcImage(Long userId, MultipartFile etcImage) throws IOException{
        Record record = recordRepository.findRecordByUserId(userId);
        if(!etcImage.isEmpty()){
            String storedFileName = s3Uploader.upload(etcImage, "etc_image");
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

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}