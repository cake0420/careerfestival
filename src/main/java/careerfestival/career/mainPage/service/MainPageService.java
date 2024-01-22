package careerfestival.career.mainPage.service;

import careerfestival.career.domain.Event;
import careerfestival.career.mainPage.dto.MainPageResponseDto;
import careerfestival.career.repository.EventRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainPageService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public List<MainPageResponseDto> getEvents() {
        // 조회수에 의한 정렬 처리 필요
        List<Event> events = eventRepository.findAll();
        
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
}
