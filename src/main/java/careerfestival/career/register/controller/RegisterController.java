package careerfestival.career.register.controller;

import careerfestival.career.register.dto.RegisterEventDto;
import careerfestival.career.register.dto.RegisterMainResponseDto;
import careerfestival.career.register.dto.RegisterOrganizerDto;
import careerfestival.career.register.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/event")
@RestController
public class RegisterController {

    private final RegisterService registerService;

    // 이벤트 등록 (eventName이랑 description만)

    @PostMapping("/organizer/{userId}")
    public ResponseEntity registerOrganizer(@PathVariable("userId") Long userId, @RequestBody RegisterOrganizerDto registerOrganizerDto) {
        registerService.registerOrganizer(userId, registerOrganizerDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/register/{userId}")
    public ResponseEntity registerEvent(@PathVariable("userId") Long userId, @RequestBody RegisterEventDto registerEventDto) {
        registerService.registerEvent(userId, registerEventDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getRegisterByUserId(@PathVariable("userId") Long userId) {
        try{
            List<RegisterMainResponseDto> registerMainResponseDto = registerService.getRegisterByUserId(userId);
            return ResponseEntity.ok(registerMainResponseDto);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}