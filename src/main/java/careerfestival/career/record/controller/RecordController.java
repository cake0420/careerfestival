package careerfestival.career.record.controller;

import careerfestival.career.record.dto.RecordEtcDto;
import careerfestival.career.record.dto.RecordLectureSeminarDto;
import careerfestival.career.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import careerfestival.career.record.dto.RecordMainResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/record")
@RestController
public class RecordController {
//    private final RecordCommandService recordCommandService;
    private final RecordService recordService;
//    private final RecordCommandService recordCommandService;
    //
    @PostMapping("/lecture-seminar/{userId}")
    public ResponseEntity<Void> recordLectureSeminar(@PathVariable("userId") Long userId, @RequestBody RecordLectureSeminarDto recordLectureSeminarDto) {
        try {
            recordService.recordLectureSeminar(userId, recordLectureSeminarDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/etc/{userId}")
    public ResponseEntity<Void> recordEtc(@PathVariable("userId") Long userId, @RequestBody RecordEtcDto recordEtcDto) {
        try {
            recordService.recordEtc(userId, recordEtcDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //기록장
//    @PostMapping("/{memberId}")
//    public ApiResponse<RecordResponseDto.AddRecordResponseDto>
//    addRecordConference(@RequestBody @Valid RecordRequestDto.AddRecordConferenceRequestDto request
//              ,@PathVariable Long memberId){
//        Record record = recordCommandService.addRecord(request, memberId);
//        return ApiResponse.onSuccess(RecordConverter.toAddRecordResponseDto(record));
//    }



    // 메인페이지
    @GetMapping("/{userId}")
    public ResponseEntity<List<RecordMainResponseDto>> getRecordsByUserId(@PathVariable("userId") Long userId) {
        try {
            List<RecordMainResponseDto> recordMainResponses = recordService.getRecordsByUserId(userId);
            return ResponseEntity.ok(recordMainResponses);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
