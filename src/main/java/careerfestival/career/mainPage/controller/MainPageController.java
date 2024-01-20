package careerfestival.career.mainPage.controller;

import careerfestival.career.mainPage.dto.MainPageResponseDto;
import careerfestival.career.mainPage.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class MainPageController {
    private final MainPageService mainPageService;


    // 로그인 이전 화면 Authorization 이전
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getEvents(){
        try{
            // 1. 조회수에 의한 이벤트명
            List<MainPageResponseDto> mainPageResponseDtoNames = mainPageService.getEventNames();
            // 2. 조회수에 의한 정렬 리스트
            List<MainPageResponseDto> mainPageResponseDtoViews = mainPageService.getEvents();
            // 3. 랜덤에 의한 정렬 리스트
            List<MainPageResponseDto> mainPageResponseDtoRandom = mainPageService.getEvents();

            Map<String, Object> mainPageResponseDtoObjectMap = new HashMap<>();
            mainPageResponseDtoObjectMap.put("eventrandom", mainPageResponseDtoRandom);
            mainPageResponseDtoObjectMap.put("eventviews", mainPageResponseDtoViews);
            mainPageResponseDtoObjectMap.put("eventnames", mainPageResponseDtoNames);

            return ResponseEntity.ok().body(mainPageResponseDtoObjectMap);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
