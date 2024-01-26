package careerfestival.career.register.controller;

import careerfestival.career.register.dto.RegisterEventDto;
import careerfestival.career.register.dto.RegisterMainResponseDto;
import careerfestival.career.register.dto.RegisterOrganizerDto;
import careerfestival.career.register.service.RegisterService;
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
@RequestMapping
@RestController
public class RegisterController {
    private final RegisterService registerService;

    // 주최자가 행사 등록 (행사 등록하기 1단계)
    @PostMapping("/event/organizer/{userId}")
    public ResponseEntity registerOrganizerName(@PathVariable("userId") Long userId, @RequestBody RegisterOrganizerDto registerOrganizerDto) {
        registerService.registerOrganizer(userId, registerOrganizerDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 주최자 프로필 이미지 업로드 (행사 등록하기 2단계)
    @PostMapping(value = "/event/organizer/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity registerOrganizerImage(@PathVariable("userId") Long userId,
                                                 HttpServletRequest request,
                                                 @RequestParam(value = "organizerProfileImage") MultipartFile organizerProfileImage){
        try{
            registerService.registerOrganizerImage(userId, organizerProfileImage);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // 행사 등록하기 1, 행사 등록하기 2 통합
    @PostMapping("/event/register/{organizerId}")
    public ResponseEntity registerEvent(@PathVariable("organizerId") Long organizerId,
                                        @RequestBody RegisterEventDto registerEventDto) {
        registerService.registerEvent(organizerId,registerEventDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 행사 대표 이미지 업로드 등록 -> 추후에 어떤 주최자가 개설한 특정 행사에 대해서 사진을 업로드하는 로직을 추가
    // requestparam에 무엇을 넣을지는 인가 완료후에 구현하기
    @PostMapping(value = "/event/register/{eventId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity regiserEventMainImage(@PathVariable("eventId") Long eventId,
                                            HttpServletRequest request,
                                            @RequestParam(value = "eventMainimage") MultipartFile eventMainimage){
        registerService.registerEventMainImage(eventId, eventMainimage);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 행사 정보 이미지 업로드 등록 -> 행사 대표 이미지 업로드 등로과 같음
    // requestparam에 무엇을 넣을지는 인가 완료후에 구현하기
    @PostMapping(value = "/event/register/{userId}/{eventId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity regiserEventInformImage(@PathVariable("userId") Long userId,
                                                  HttpServletRequest request,
                                                  @RequestParam(value = "eventInformimage") MultipartFile eventInformimage){
        try{
            registerService.registerEventInformImage(userId, eventInformimage);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // 주최자가 자신의 프로필을 확인할 수 있음 (행사 등록하기 5단계) 바로
    @GetMapping("profile/register/{organizerId}")
    public ResponseEntity<Page<RegisterMainResponseDto>> getRegisterByOrganizerId(
            @PathVariable("organizerId") Long organizerId,
            @PageableDefault(size = 4, sort = "updated_at", direction = Sort.Direction.DESC)Pageable pageable) {
        try{
            Page<RegisterMainResponseDto> registerMainResponseDtos = registerService.getEventList(organizerId, pageable);
            return ResponseEntity.ok(registerMainResponseDtos);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}