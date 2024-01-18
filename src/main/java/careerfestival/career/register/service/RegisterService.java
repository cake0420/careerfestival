package careerfestival.career.register.service;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.register.dto.RegisterEventDto;
import careerfestival.career.repository.EventRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public void registerEvent(Long userId, RegisterEventDto registerEventDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id" + userId));
        Event event = registerEventDto.toEntity();
        event.setUser(user);

        eventRepository.save(event);
    }
}
