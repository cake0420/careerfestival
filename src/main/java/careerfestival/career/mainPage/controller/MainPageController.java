package careerfestival.career.mainPage.controller;

import careerfestival.career.domain.enums.Category;
import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.domain.mapping.Region;
import careerfestival.career.login.dto.CustomUserDetails;
import careerfestival.career.mainPage.dto.MainPageFestivalListResponseDto;
import careerfestival.career.mainPage.dto.MainPageResponseDto;
import careerfestival.career.mainPage.service.MainPageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class MainPageController {
    private final MainPageService mainPageService;

    // 로그인 이전 화면 Authorization 이전
    @Transactional
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getEvents(){
        try{
            // 1. 조회수에 의한 이벤트명
            List<MainPageResponseDto> mainPageResponseDtoNames = mainPageService.getEventNames();
            // 2. 조회수에 의한 정렬 리스트
            List<MainPageResponseDto> mainPageResponseDtoViews = mainPageService.getEventsHitsDesc();
            // 3. 랜덤에 의한 정렬 리스트
            List<MainPageResponseDto> mainPageResponseDtoRandom = mainPageService.getEventsHitsRandom();

            Map<String, Object> mainPageResponseDtoObjectMap = new HashMap<>();
            mainPageResponseDtoObjectMap.put("eventRandom", mainPageResponseDtoRandom);
            mainPageResponseDtoObjectMap.put("eventViews", mainPageResponseDtoViews);
            mainPageResponseDtoObjectMap.put("eventNames", mainPageResponseDtoNames);

            return ResponseEntity.ok().body(mainPageResponseDtoObjectMap);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @Transactional
//    @GetMapping("")
//    public ResponseEntity<Map<String, Object>> getEvents(@AuthenticationPrincipal CustomUserDetails customUserDetails){
//        boolean exists = mainPageService.findExistUserByCustomUserDetails(customUserDetails);
//        try{
//            if(exists){
//                try{
//                    // 1. 조회수에 의한 이벤트명
//                    // 1. 조회수에 의한 이벤트명
//                    List<MainPageResponseDto> mainPageResponseDtoNames = mainPageService.getEventNames();
//                    // 2. 조회수에 의한 정렬 리스트
//                    List<MainPageResponseDto> mainPageResponseDtoViews = mainPageService.getEventsHitsDesc();
//                    // 3. 랜덤에 의한 정렬 리스트
//                    List<MainPageResponseDto> mainPageResponseDtoRandom = mainPageService.getEventsHitsRandom();
//
//                    Map<String, Object> mainPageResponseDtoObjectMap = new HashMap<>();
//                    mainPageResponseDtoObjectMap.put("eventRandom", mainPageResponseDtoRandom);
//                    mainPageResponseDtoObjectMap.put("eventViews", mainPageResponseDtoViews);
//                    mainPageResponseDtoObjectMap.put("eventNames", mainPageResponseDtoNames);
//
//                    return ResponseEntity.ok().body(mainPageResponseDtoObjectMap);
//                } catch (IllegalArgumentException e){
//                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//                }
//            } else{
//                String redirectUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
//                        .path("")
//                        .toUriString();
//
//                HttpHeaders headers = new HttpHeaders();
//                headers.add("Location", redirectUrl);
//                return new ResponseEntity<>(headers, HttpStatus.FOUND);
//            }
//        } catch (IllegalArgumentException e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }


    // 메인페이지에서 행사 목록 클릭 시 보여지는 화면
    // Category와 keywordName에 의해서 필터링되고 페이징 적용
    @GetMapping("/festival-list")
    public ResponseEntity<Map<String, Object>> getEventsFilter(
            @RequestParam(value = "category") List<Category> category,
            @RequestParam(value = "keywordName") List<KeywordName> keywordName,
            @RequestParam(value = "region") Region region,          // 지역은 1개에 대해서만 필터링 진행
            @PageableDefault(size = 9, sort = "hits", direction = Sort.Direction.DESC) Pageable pageable){
        try{
            // 필터링이 적용될 9개의 행사 리스트 조회
            Page<MainPageFestivalListResponseDto> mainPageFestivalListResponseDtos
                    = mainPageService.getEventsFiltered(category, keywordName, region, pageable);
            // 조회수에 의한 이벤트명
            List<MainPageResponseDto> mainPageResponseDtoNames = mainPageService.getEventNames();

            Map<String, Object> mainPageFestivalListResponseDtoObjectMap = new HashMap<>();
            // eventFilter는 메인페이지의 필터링이 적용된 페이지 행사 리스트들
            mainPageFestivalListResponseDtoObjectMap.put("eventFilter", mainPageFestivalListResponseDtos);
            // eventNames는 상단바에 위치한 조회수에 의한 행사명 리스트
            mainPageFestivalListResponseDtoObjectMap.put("eventNames", mainPageResponseDtoNames);

            return ResponseEntity.ok().body(mainPageFestivalListResponseDtoObjectMap);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
