package careerfestival.career.register.controller;

import careerfestival.career.register.dto.RegisterEventDto;
import careerfestival.career.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class RegisterController {

    private final RegisterService registerService;

    // 이벤트 등록 (eventName이랑 description만)
    @PostMapping("/event/{userId}")
    public ResponseEntity registerEvent(@PathVariable("userId") Long userId, @RequestBody RegisterEventDto registerEventDto) {
        registerService.registerEvent(userId, registerEventDto);
        return new ResponseEntity(HttpStatus.OK);
    }

}