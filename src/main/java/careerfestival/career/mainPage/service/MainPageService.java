package careerfestival.career.mainPage.service;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.enums.Category;
import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.mainPage.dto.MainPageFestivalListResponseDto;
import careerfestival.career.mainPage.dto.MainPageResponseDto;
import careerfestival.career.repository.EventRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainPageService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public List<MainPageResponseDto> getEventsHitsDesc() {
        // 조회수에 의한 내림차순 정렬한 events
        List<Event> events = eventRepository.findAllByOrderByHitsDesc();
        
        return events.stream()
                .map(MainPageResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<MainPageResponseDto> getEventNames() {
        // 조회수에 의한 정렬 처리 필요
        List<Event> eventNames = eventRepository.findAll();

        return eventNames.stream()
                .map(MainPageResponseDto::fromEntityName)
                .collect(Collectors.toList());
    }

    public List<MainPageResponseDto> getEventsHitsRandom() {
        List<Event> events = eventRepository.findRandomEvents(3);

        return events.stream()
                .map(MainPageResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<MainPageFestivalListResponseDto> getEventsFiltered(Category category, KeywordName keywordName) {
        List<Event> events = eventRepository.findAllByCategoryKeywordName(category, keywordName);
        return events.stream()
                .map(MainPageFestivalListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
