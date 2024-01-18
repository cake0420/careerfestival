package careerfestival.career.register.service;

import careerfestival.career.domain.Event;
import careerfestival.career.register.dto.RegisterEventDto;
import careerfestival.career.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public Long registerEvent(RegisterEventDto registerEventDto) {
        Event event = registerEventDto.toEntity();
        eventRepository.save(event);
        return event.getId();
    }

    // 행사 유형 모두 찾기
    public List<Category> findCategories() {
        return categoryRepository.findAll();
    }
}
