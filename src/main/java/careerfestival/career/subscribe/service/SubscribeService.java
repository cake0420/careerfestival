//package careerfestival.career.subscribe.service;
//
//import careerfestival.career.domain.User;
//import careerfestival.career.domain.mapping.Subscribe;
//import careerfestival.career.participate.Exception.UserOrEventNotFoundException;
//import careerfestival.career.repository.SubscribeRepository;
//import careerfestival.career.repository.UserRepository;
//import careerfestival.career.subscribe.dto.SubscribeRequestDto;
//import careerfestival.career.subscribe.dto.SubscribeResponseDto;
//import careerfestival.career.wish.dto.WishResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class SubscribeService {
//    private final UserRepository userRepository;
//    private final SubscribeRepository subscribeRepository;
//    public boolean addRemove(Long userId, SubscribeRequestDto subscribeRequestDto){
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//
//            Subscribe check = (Subscribe) subscribeRepository.findByUserId(user.getId());
//            if (check == null) {
//                Subscribe subs = subscribeRequestDto.toEntity(user);
//                subscribeRepository.save(subs);
//                return true;
//            } else {
//                subscribeRepository.delete(check);
//                return false;
//            }
//        }else {
//            throw new UserOrEventNotFoundException("User or Event not found");
//        }
//    }
//    public List<Long> getAllSubscribeByUser(Long userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//
//            List<Subscribe> subscriptions = subscribeRepository.findByUserId(user.getId());
//
//            return subscriptions.stream()
//                    .map(subscribe -> subscribe.getUser().getId())
//                    .collect(Collectors.toList());
//        } else {
//            throw new UserOrEventNotFoundException("User or Event not found");
//        }
//    }
//}
