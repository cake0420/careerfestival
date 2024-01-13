package careerfestival.career.login.service;

import careerfestival.career.domain.Role;
import careerfestival.career.domain.User;
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

    public void signUp(UserSignUpRequestDto userSignUpRequestDto) {
        String email = userSignUpRequestDto.getEmail();
        String password = userSignUpRequestDto.getPassword();
        String name = userSignUpRequestDto.getName();

        boolean exists = userRepository.existsByEmail(email);
        if (exists) {
            return;
        }

        User data = new User();
        data.setEmail(email);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setName(name);

        userRepository.save(data);
    }


    public void signRole(Long user_id, UserSignRoleRequestDto userSignRoleRequestDto) {

        System.out.println("----------------------------------------");
        Optional<User> userOptional = userRepository.findById(user_id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Role newRole = userSignRoleRequestDto.getRole();

            // 사용자의 Role 업데이트
            user.setRole(newRole);

            // 변경 사항 저장
            userRepository.save(user);
        } else {
            // 사용자가 존재하지 않는 경우, 적절한 예외 처리
            throw new RuntimeException("User not found with user_id: " + user_id);
        }

    }


}
