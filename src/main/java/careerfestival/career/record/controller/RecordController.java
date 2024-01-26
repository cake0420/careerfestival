package careerfestival.career.record.controller;

import careerfestival.career.record.dto.RecordRequestDto;
import careerfestival.career.record.service.RecordConferenceExhibitionService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RequiredArgsConstructor
@RequestMapping("/record")
@RestController
public class RecordController {
    private final RecordConferenceExhibitionService recordConferenceService;
    private final RecordService recordService;

    @PostMapping("/lecture-seminar/{userId}")
    public ResponseEntity<Void> recordLectureSeminar(@PathVariable("userId") Long userId, @RequestBody RecordLectureSeminarRequestDto recordLectureSeminarRequestDto) {
        try {
            recordService.recordLectureSeminar(userId, recordLectureSeminarRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/lecture-seminar/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity recordLectureSeminarImage(@PathVariable("userId") Long userId,
                                                    HttpServletRequest request,
                                                    @RequestParam(value = "lectureseminarimage")MultipartFile lectureseminarimage){
        try{
            recordService.recordLectureSeminarImage(userId, lectureseminarimage);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/etc/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity recordEtcImage(@PathVariable("userId") Long userId,
                                         HttpServletRequest request,
                                         @RequestParam(value = "etcimage") MultipartFile etcimage){
        try{
            recordService.recordEtcImage(userId, etcimage);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/etc/{userId}")
    public ResponseEntity<Void> recordEtc(@PathVariable("userId") Long userId, @RequestBody RecordEtcRequestDto recordEtcRequestDto) {
        try {
            recordService.recordEtc(userId, recordEtcRequestDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
  
    // conference 기록 추가
    @PostMapping("/conference/{userId}")
    public ResponseEntity<Void> addRecordConference(@RequestBody @Valid RecordRequestDto request, @PathVariable("userId") Long userId) {
        try {
            recordConferenceService.recordConference(userId,request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    @PostMapping("/exhibition/{memberId}")
//    public ResponseEntity<Void> addRecordExhibition(@RequestBody @Valid RecordRequestDto request, @PathVariable("userId") Long userId) {
//        try {
//            recordConferenceService.recordExhibition(userId, request);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/{userId}")
    public ResponseEntity<Page<RecordMainResponseDto>> getRecordsByUserId(
            @PathVariable("userId") Long userId,
            @PageableDefault(size = 4, sort = "updated_at", direction = Sort.Direction.DESC) Pageable pageable) {
        try {
            Page<RecordMainResponseDto> recordMainResponseDtos = recordService.recordList(userId, pageable);
            return ResponseEntity.ok(recordMainResponseDtos);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lecture-seminar")
    public ResponseEntity<RecordLectureSeminarResponseDto> getRecordLectureSeminarByRecordId(@PathVariable("recordId") Long recordId){
        try{
            RecordLectureSeminarResponseDto recordLectureSeminarResponseDto = recordService.getLectureSeminar(recordId);
            return ResponseEntity.ok(recordLectureSeminarResponseDto);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/etc")
    public ResponseEntity<RecordEtcResponseDto> getRecordEtcByRecordId(@PathVariable("recordId") Long recordId) {
        try{
            RecordEtcResponseDto recordEtcResponseDto = recordService.getEtc(recordId);
            return ResponseEntity.ok(recordEtcResponseDto);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
