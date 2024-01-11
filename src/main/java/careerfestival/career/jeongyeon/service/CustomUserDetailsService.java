package careerfestival.career.jeongyeon.service;

import careerfestival.career.jeongyeon.domain.User;
import careerfestival.career.jeongyeon.repository.UserRepository;
import careerfestival.career.jeongyeon.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //DB에서 조회
        User findByEmail = userRepository.findByEmail(email);

        if (findByEmail != null) {
            return new CustomUserDetails(findByEmail);
        }
        return null;
    }
}
