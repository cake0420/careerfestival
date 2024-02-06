package careerfestival.career.participate.service;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Participate;
import careerfestival.career.participate.Exception.UserOrEventNotFoundException;
import careerfestival.career.participate.dto.ParticipateRequestDto;
import careerfestival.career.participate.dto.ParticipateResponseDto;
import careerfestival.career.repository.EventRepository;
import careerfestival.career.repository.ParticipateRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipateService {
    private final ParticipateRepository participateRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public Long participateSave(Long userId, Long eventId, ParticipateRequestDto participateRequestDto) {

        Optional<User> user = userRepository.findById(userId);
        Optional<Event> event = eventRepository.findById(eventId);
        System.out.println("event = " + event);
        Participate participate = participateRequestDto.toEntity(user.orElse(null), event.orElse(null));
        participateRepository.save(participate);
        return participate.getId();
    }

    public List<ParticipateResponseDto> getAllParticipateByEvent(Long userId, Long eventId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (userOptional.isPresent() && eventOptional.isPresent()) {
            User user = userOptional.get();
            Event event = eventOptional.get();

            List<Participate> participates = participateRepository.findByUserAndEvent(user, event);

            return participates.stream()
                    .map(ParticipateResponseDto::new)
                    .collect(Collectors.toList());
        } else {
            // 원하는 예외 처리 또는 에러 응답을 수행할 수 있습니다.
            throw new UserOrEventNotFoundException("User or Event not found");
        }
    }

    public String getLink(Long eventId){
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null){
            if(event.getLink() != null){
                return event.getLink();
            }
            return null;
        }
        else {
            // 원하는 예외 처리 또는 에러 응답을 수행할 수 있습니다.
            throw new UserOrEventNotFoundException("Event not found");
        }
    }
}

