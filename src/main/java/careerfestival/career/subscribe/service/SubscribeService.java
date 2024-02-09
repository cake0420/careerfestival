package careerfestival.career.subscribe.service;

import careerfestival.career.AES.AESUtil;
import careerfestival.career.domain.User;
import careerfestival.career.domain.enums.Role;
import careerfestival.career.domain.mapping.Organizer;
import careerfestival.career.domain.mapping.Subscribe;
import careerfestival.career.participate.Exception.UserOrEventNotFoundException;
import careerfestival.career.repository.OrganizerRepository;
import careerfestival.career.repository.SubscribeRepository;
import careerfestival.career.repository.UserRepository;
import careerfestival.career.subscribe.dto.SubscribeRequestDto;
import careerfestival.career.subscribe.dto.SubscribeResponseDto;
import careerfestival.career.wish.dto.WishResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
    public class SubscribeService {
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final OrganizerRepository organizerRepository;
    private final AESUtil aesUtil;

    public boolean addRemove(Long userId, SubscribeRequestDto subscribeRequestDto) {
        String fromUserId = subscribeRequestDto.getFromUser();
        String decrypt = aesUtil.decrypt(fromUserId);
        Optional<User> toUserOptional = userRepository.findById(userId);
        User fromUserOptional = userRepository.findByEmail(decrypt);

        if (toUserOptional.isEmpty( ) ||  fromUserOptional.getId() == null) {
            throw new UserOrEventNotFoundException("User not found");
        }

        User toUser = toUserOptional.get();
        Organizer fromUser = organizerRepository.findByUserId(fromUserOptional.getId());

        Subscribe subscribe = subscribeRepository.findByFromUser_IdAndToUser_Id(fromUser.getId(), userId);
        if (subscribe == null && toUser.getRole() == Role.ROLE_PARTICIPANT) {

            subscribeRepository.save(new Subscribe(toUser, fromUser));
            return true;
        } else if (subscribe != null && toUser.getRole() == Role.ROLE_PARTICIPANT) {
            subscribeRepository.delete(subscribe);
            return true;
        }
        return false;
    }


        public List<SubscribeResponseDto> getAllSubscribeByUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserOrEventNotFoundException("User not found");
        } else {
            List<Subscribe> subscribes = subscribeRepository.findByFromUserId(userId);
            return subscribes.stream()
                    .map(SubscribeResponseDto::new)
                    .collect(Collectors.toList());
        }
    }

    public int countFollower (Organizer user){
        int counted = subscribeRepository.findByFromUser(user);
        return counted;
    }
}
