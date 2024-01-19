package careerfestival.career.login.service;

import careerfestival.career.domain.User;
import careerfestival.career.repository.UserRepository;
import careerfestival.career.login.dto.CustomUserDetails;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        User findByEmail = userRepository.findByEmail(username);

        if (findByEmail != null) {
            return new CustomUserDetails(findByEmail);
        }
        return null;
    }

}
