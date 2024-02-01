package careerfestival.career.record.controller;

import careerfestival.career.login.dto.CustomUserDetails;
import careerfestival.career.record.dto.RecordRequestDto;
import careerfestival.career.record.service.RecordService;
import jakarta.validation.Valid;
import careerfestival.career.record.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/record")
@RestController
public class RecordController {
    private final RecordService recordService;

    // 사용자가 기록장(강연/세미나) 게시하는 경우 - 처음 게시하는 작업이니까 userId로 접근하기
    @PostMapping("/lecture-seminar")
    public ResponseEntity<Void> recordLectureSeminar(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                     @RequestBody RecordRequestDto recordRequestDto) {
        try {
            recordService.recordLectureSeminar(customUserDetails.getUsername(), recordRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // conference 기록 추가
    @PostMapping("/conference")
    public ResponseEntity<Void> recordConference(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                 @RequestBody @Valid RecordRequestDto request){
        try {
            recordService.recordConference(customUserDetails.getUsername(), request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/exhibition/{userId}")
    public ResponseEntity<Void> recordExhibition(@PathVariable("userId") Long userId,
                                                 @RequestBody @Valid RecordRequestDto request){
        try {
            recordService.recordExhibition(userId,request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/etc/{userId}")
    public ResponseEntity<Void> recordEtc(@PathVariable("userId") Long userId,
                                          @RequestBody RecordRequestDto recordRequestDto) {
        try {
            recordService.recordEtc(userId, recordRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 이미 이미지를 제외한 기록장 정보가 db에 1차적으로 저장되어 있는 상황 -> 이미지 추가 저장의 경우 저장되어 있는 recordId를 통해 이루어짐
    @PostMapping(value = "/lecture-seminar/{recordId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity recordLectureSeminarImage(
            @PathVariable("recordId") Long recordId,
            HttpServletRequest request,
            @RequestParam(value = "lectureSeminarImage") MultipartFile lectureSeminarImage){
        try{
            recordService.recordLectureSeminarImage(recordId, lectureSeminarImage);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/conference/{recordId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity recordConferenceImage(
            @PathVariable("recordId") Long recordId,
            HttpServletRequest request,
            @RequestParam(value = "conferenceImage") List<MultipartFile> conferenceImage){
        try{
            recordService.recordConferenceImage(recordId, conferenceImage);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/exhibition/{recordId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity recordExhibitionImage(
            @PathVariable("recordId") Long recordId,
            HttpServletRequest request,
            @RequestParam(value = "exhibitionImage") List<MultipartFile> exhibitionImage){
        try{
            recordService.recordExhibitionImage(recordId, exhibitionImage);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/etc/{recordId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity recordEtcImage(@PathVariable("recordId") Long recordId,
                                         HttpServletRequest request,
                                         @RequestParam(value = "etcImage") MultipartFile etcImage){
        try{
            recordService.recordEtcImage(recordId, etcImage);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }



    // 메인페이지
    @GetMapping("/{userId}")
    public ResponseEntity<Page<RecordResponseDto>> getRecordsByUserId(
            @PathVariable("userId") Long userId,
            @PageableDefault(size = 4, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        try {
            Page<RecordResponseDto> recordResponseDtos = recordService.recordList(userId, pageable);
            return ResponseEntity.ok(recordResponseDtos);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category/{recordId}")
    public ResponseEntity<RecordResponseDto> getRecordByRecordId(
            @PathVariable("recordId") Long recordId){
        try{
            RecordResponseDto recordResponseDto = recordService.getRecord(recordId);
            return ResponseEntity.ok(recordResponseDto);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
