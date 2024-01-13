package careerfestival.career.login.service;

import careerfestival.career.domain.Role;
import careerfestival.career.domain.User;
import careerfestival.career.login.dto.UserSignDetailRequestDto;
import careerfestival.career.login.dto.UserSignInRequestDto;
import careerfestival.career.login.dto.UserSignRoleRequestDto;
import careerfestival.career.login.dto.UserSignUpRequestDto;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long signUp(UserSignUpRequestDto userSignUpRequestDto) {
        User user = userSignUpRequestDto.toEntity();

        // DB에 존재하는지 여부 (email로 판단)
        boolean exists = userRepository.existsByEmail(userSignUpRequestDto.getEmail());
        if(exists){
            return null;
        }
        user.setPassword(bCryptPasswordEncoder.encode(userSignUpRequestDto.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    public void signRole(Long userId, UserSignRoleRequestDto userSignRoleRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(userSignRoleRequestDto.getRole());
        userRepository.save(user);
    }

    public void signDetail(Long userId, UserSignDetailRequestDto userSignDetailRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setGender(userSignDetailRequestDto.getGender());
        user.setAge(userSignDetailRequestDto.getAge());
        user.setPhoneNumber(userSignDetailRequestDto.getPhoneNumber());

        userRepository.save(user);
    }
}
