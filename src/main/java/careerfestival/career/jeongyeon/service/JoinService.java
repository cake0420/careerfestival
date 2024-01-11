package careerfestival.career.jeongyeon.service;

import careerfestival.career.jeongyeon.domain.User;
import careerfestival.career.jeongyeon.repository.UserRepository;
import careerfestival.career.jeongyeon.dto.JoinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinDTO joinDTO) {

        String email = joinDTO.getEmail();
        String password = joinDTO.getPassword();
        String role = joinDTO.getRole();

        boolean exists = userRepository.existsByEmail(email);
        if (exists) {
            return;
        }

        User data = new User();

        data.setEmail(email);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_" + role);


        userRepository.save(data);
    }
}
