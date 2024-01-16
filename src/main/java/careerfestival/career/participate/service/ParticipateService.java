package careerfestival.career.participate.service;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Participate;
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

    public Long participateSave(Long userId, Long eventId, ParticipateRequestDto requestDto) {
        Optional<Participate> participate = participateRepository.findById(requestDto.getId());
        Optional<User> userid = userRepository.findById(userId);
        Optional<Event> eventid = eventRepository.findById(eventId);

        Participate participates = Participate.builder()
                .id(requestDto.getId())
                .build();

        Participate savedParticipate = participateRepository.save(participates);
        return savedParticipate.getId();
    }

    public List<ParticipateResponseDto> getAllParticipateByEvent(Long userId, Long eventId, String nam) {
        Optional<User> userid = userRepository.findById(userId);
        Optional<Event> eventid = eventRepository.findById(eventId);
        List<Participate> participates = participateRepository.findByEvent_EventName(nam);
        return participates.stream()
                .map(ParticipateResponseDto::new)
                .collect(Collectors.toList());

    }
}

