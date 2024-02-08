package careerfestival.career.subscribe.service;

import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Organizer;
import careerfestival.career.domain.mapping.Subscribe;
import careerfestival.career.participate.Exception.UserOrEventNotFoundException;
import careerfestival.career.repository.OrganizerRepository;
import careerfestival.career.repository.SubscribeRepository;
import careerfestival.career.repository.UserRepository;
import careerfestival.career.subscribe.dto.SubscribeRequestDto;
import careerfestival.career.subscribe.dto.SubscribeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
    public class SubscribeService {
    private final UserRepository userRepository;
    private final OrganizerRepository organizerRepository;
    private final SubscribeRepository subscribeRepository;


    public boolean addRemove(SubscribeRequestDto subscribeRequestDto) {
        Long toUserId = subscribeRequestDto.getToUser();
        Long subscribedOrganizerId = subscribeRequestDto.getSubscribedOrganizer();

        Optional<User> toUserOptional = userRepository.findById(toUserId);
        Optional<Organizer> fromOrganizerOptional = organizerRepository.findById(subscribedOrganizerId);

        if (toUserOptional.isEmpty() || fromOrganizerOptional.isEmpty()) {
            throw new UserOrEventNotFoundException("User not found");
        }

        User toUser = toUserOptional.get();
        Organizer subscribedOrganizer = fromOrganizerOptional.get();

        Subscribe subscribe = subscribeRepository.findBySubscribedOrganizer_IdAndToUser_id(subscribedOrganizerId, toUserId);
        if (subscribe == null) {

            subscribeRepository.save(new Subscribe(toUser, subscribedOrganizer));
            return true;
        } else {
            subscribeRepository.delete(subscribe);
            return false;
        }
    }


        public List<SubscribeResponseDto> getAllSubscribeByUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserOrEventNotFoundException("User not found");
        } else {
            List<Subscribe> subscribes = subscribeRepository.findBySubscribedOrganizerId(userId);
            return subscribes.stream()
                    .map(SubscribeResponseDto::new)
                    .collect(Collectors.toList());
        }
    }

    public int countFollower (Organizer organizer){
        int counted = subscribeRepository.findBySubscribedOrganizer(organizer);
        return counted;
    }

    public Organizer getOrganizer(Long userId) {
        return organizerRepository.findByUserId(userId);
    }
}
