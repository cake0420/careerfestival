package careerfestival.career.register.service;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Organizer;
import careerfestival.career.register.dto.RegisterEventDto;
import careerfestival.career.register.dto.RegisterMainResponseDto;
import careerfestival.career.register.dto.RegisterOrganizerDto;
import careerfestival.career.repository.EventRepository;
import careerfestival.career.repository.OrganizerRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;

    public void registerEvent(Long userId, RegisterEventDto registerEventDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id" + userId));
        Event event = registerEventDto.toEntity();
        event.setUser(user);

        eventRepository.save(event);
    }

    public void registerOrganizer(Long userId, RegisterOrganizerDto registerOrganizerDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id" + userId));

        Organizer organizer = registerOrganizerDto.toEntity();
        organizer.setUser(user);

        organizerRepository.save(organizer);
    }

    public List<RegisterMainResponseDto> getRegisterByUserId(Long userId) {
        List<Event> events = eventRepository.findByUserId(userId);

        return events.stream()
                .map(RegisterMainResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
