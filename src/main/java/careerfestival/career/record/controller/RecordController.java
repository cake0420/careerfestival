package careerfestival.career.record.controller;

import careerfestival.career.record.dto.RecordEtcDto;
import careerfestival.career.record.dto.RecordLectureSeminarDto;
import careerfestival.career.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/record")
@RestController
public class RecordController {
    private final RecordService recordService;

    @PostMapping("/lecture-seminar")
    public ResponseEntity<Void> recordLectureSeminar(@RequestParam("userId") Long userId, @RequestBody RecordLectureSeminarDto recordLectureSeminarDto) {
        try {
            recordService.recordLectureSeminar(userId, recordLectureSeminarDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // User가 Keyword 테이블에서 선택한다고 했을 때, Keyword_id를 넘겨주는 방법은 어떤지? (어차피 Keyword는 정해져 있으니까?)
    @PostMapping("/etc")
    public ResponseEntity<Void> recordEtc(@RequestParam("userId") Long userId, @RequestBody RecordEtcDto recordEtcDto) {
        try {
            recordService.recordEtc(userId, recordEtcDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
