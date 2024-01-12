package careerfestival.career.service;

import careerfestival.career.domain.User;
import careerfestival.career.repository.UserRepository;
import careerfestival.career.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        User findByEmail = userRepository.findByEmail(username);

        System.out.println("------------------------------------------------");
        System.out.println("findByEmail = " + findByEmail);

        if (findByEmail != null) {
            return new CustomUserDetails(findByEmail);
        }
        return null;
    }


}
