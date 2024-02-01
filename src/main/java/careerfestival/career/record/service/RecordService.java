package careerfestival.career.record.service;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.NetworkDetail;
import careerfestival.career.domain.mapping.RecordDetail;
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
import java.util.List;


@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    @Autowired
    private S3Uploader s3Uploader;

    // 게시판 등록
    @Transactional
    public void recordLectureSeminar(String email, RecordRequestDto recordRequestDto) {
        // 이미지 첨부 및 글자 수 제한 적용 필요
        User user = userRepository.findByEmail(email);

        Record record = recordRequestDto.toEntity();
        record.setUser(user);

        if(recordRequestDto.getRecordDetails() != null){
            for(RecordDetail recordDetail : recordRequestDto.getRecordDetails()){
                recordDetail.setRecord(record); // RecordDetail에 Record 설정
            }
        }

        if (recordRequestDto.getNetworkDetails() != null) {
            for (NetworkDetail networkDetail : recordRequestDto.getNetworkDetails()) {
                networkDetail.setRecord(record); // NetworkDetail에 Record 설정
            }
        }
        recordRepository.save(record);
    }
    @Transactional
    public void recordConference(String email, RecordRequestDto recordRequestDto) {
        User user = userRepository.findByEmail(email);

        Record record = recordRequestDto.toEntity();
        record.setUser(user);

        if(recordRequestDto.getRecordDetails() != null){
            for(RecordDetail recordDetail : recordRequestDto.getRecordDetails()){
                recordDetail.setRecord(record); // RecordDetail에 Record 설정
            }
        }

        if (recordRequestDto.getNetworkDetails() != null) {
            for (NetworkDetail networkDetail : recordRequestDto.getNetworkDetails()) {
                networkDetail.setRecord(record); // NetworkDetail에 Record 설정
            }
        }
        recordRepository.save(record);
    }
    @Transactional
    public void recordExhibition(Long userId, RecordRequestDto recordRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Record record = recordRequestDto.toEntity();
        record.setUser(user);

        if(recordRequestDto.getRecordDetails() != null){
            for(RecordDetail recordDetail : recordRequestDto.getRecordDetails()){
                recordDetail.setRecord(record); // RecordDetail에 Record 설정
            }
        }

        if (recordRequestDto.getNetworkDetails() != null) {
            for (NetworkDetail networkDetail : recordRequestDto.getNetworkDetails()) {
                networkDetail.setRecord(record); // NetworkDetail에 Record 설정
            }
        }
        recordRepository.save(record);
    }
    @Transactional
    public void recordEtc(Long userId, RecordRequestDto recordRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found with id: " + userId));

        Record record = recordRequestDto.toEntity();
        record.setUser(user);

        if(recordRequestDto.getRecordDetails() != null){
            for(RecordDetail recordDetail : recordRequestDto.getRecordDetails()){
                recordDetail.setRecord(record); // RecordDetail에 Record 설정
            }
        }

        if (recordRequestDto.getNetworkDetails() != null) {
            for (NetworkDetail networkDetail : recordRequestDto.getNetworkDetails()) {
                networkDetail.setRecord(record); // NetworkDetail에 Record 설정
            }
        }
        recordRepository.save(record);
    }








    // 기록장 이미지 업로드 구현
    @Transactional
    public void recordLectureSeminarImage(Long recordId, MultipartFile lectureSeminarImage) throws IOException {
        try {
            if (!lectureSeminarImage.isEmpty()) {
                String storedFileName = s3Uploader.upload(lectureSeminarImage, "lecture_seminar_image");
                for(RecordDetail recordDetail : recordRepository.findRecordById(recordId).getRecordDetails()){
                    recordDetail.setDescriptionFileUrl(storedFileName);
                }
            }
        } catch (Exception e){
                e.printStackTrace();
        }
    }

    @Transactional
    public void recordConferenceImage(Long recordId, List<MultipartFile> conferenceImage) throws IOException{
        try{
            if(!conferenceImage.isEmpty()){
                List<String> storedFileNames = s3Uploader.upload(conferenceImage, "conference_image");
                List<RecordDetail> recordDetails = recordRepository.findRecordById(recordId).getRecordDetails();

                int numberofDetails = recordDetails.size();
                for(int i = 0; i < numberofDetails; i++){
                    if (i < storedFileNames.size()){
                        RecordDetail recordDetail = recordDetails.get(i);
                        recordDetail.setDescriptionFileUrl(storedFileNames.get(i));
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Transactional
    public void recordExhibitionImage(Long recordId, List<MultipartFile> exhibitionImage) throws IOException{
        try{
            if(!exhibitionImage.isEmpty()){
                List<String> storedFileNames = s3Uploader.upload(exhibitionImage, "exhibition_image");
                List<RecordDetail> recordDetails = recordRepository.findRecordById(recordId).getRecordDetails();

                int numberofDetails = recordDetails.size();
                for(int i = 0; i < numberofDetails; i++){
                    if (i < storedFileNames.size()){
                        RecordDetail recordDetail = recordDetails.get(i);
                        recordDetail.setDescriptionFileUrl(storedFileNames.get(i));
                    } else {
                        break;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Transactional
    public void recordEtcImage(Long recordId, MultipartFile etcImage) throws IOException{
        try{
            if(!etcImage.isEmpty()){
                String storedFileName = s3Uploader.upload(etcImage, "etc_image");
                for(RecordDetail recordDetail : recordRepository.findRecordById(recordId).getRecordDetails()){
                    recordDetail.setDescriptionFileUrl(storedFileName);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }








    // 사용자별 기록장 메인페이지 페이징 기법 적용해서 반환 (updatedAt) 기준 내림차순 정렬
    public Page<RecordResponseDto> recordList(Long userId, Pageable pageable) {
        Page<Record> records = recordRepository.findByUserId(userId, pageable);
        return records.map(RecordResponseDto::mainFromEntity);
    }

    // 기록한 전체 레코드 반환 (찾고자 하는 기록장에 대해서)
    public RecordResponseDto getRecord(Long recordId) {
        Record record = recordRepository.findRecordById(recordId);
        return RecordResponseDto.recordFromEntity(record);
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex >= 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}