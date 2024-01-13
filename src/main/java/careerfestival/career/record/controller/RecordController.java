package careerfestival.career.record.controller;

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
}
