package careerfestival.career.participate.service;


import careerfestival.career.comments.dto.CommentResponseDto;
import careerfestival.career.domain.Comment;
import careerfestival.career.domain.Event;
import careerfestival.career.domain.mapping.Participate;
import careerfestival.career.domain.User;
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

    public Long participateSave(String email, String eventName, ParticipateRequestDto requestDto) {
        Optional<Participate> participate = participateRepository.findById(requestDto.getId());
        User user = userRepository.findByEmail(email);
        Event event = eventRepository.findByEventName(eventName);

        Participate participates = Participate.builder()
                .id(requestDto.getId())
                .user(user)
                .event(event)
                .build();

        Participate savedParticipate = participateRepository.save(participates);
        return savedParticipate.getId();
    }

    public List<ParticipateResponseDto> getAllParticipateByEvent(String eventName) {
        List<Participate> participates = participateRepository.findByEvent_EventName(eventName);
        return participates.stream()
                .map(ParticipateResponseDto::new)
                .collect(Collectors.toList());

    }
}

