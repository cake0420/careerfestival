package careerfestival.career.login.service;

import careerfestival.career.domain.User;
import careerfestival.career.login.dto.UpdateUserDetailRequestDto;
import careerfestival.career.login.dto.UserSignInRequestDto;
import careerfestival.career.login.dto.UserSignUpRequestDto;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static careerfestival.career.domain.enums.Role.ROLE_ORGANIZER;
import static careerfestival.career.domain.enums.Role.ROLE_PARTICIPANT;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long signUp(UserSignUpRequestDto userSignUpRequestDto) {

        // DB에 존재하는지 여부 (email로 판단)
        boolean exists = userRepository.existsByEmail(userSignUpRequestDto.getEmail());
        if(exists){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
        System.out.println("userSignUpRequestDto.getPassword() = " + userSignUpRequestDto.getPassword());
        System.out.println("userSignUpRequestDto.getCheckPassword() = " + userSignUpRequestDto.getCheckPassword());

        //비밀번호 입력 확인
        if(!userSignUpRequestDto.getPassword().equals(userSignUpRequestDto.getCheckPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }



        User user = userSignUpRequestDto.toEntity();
        user.updatePassword(bCryptPasswordEncoder.encode(userSignUpRequestDto.getPassword()));

        userRepository.save(user);

        return user.getId();
    }


    @Transactional
    public void updateDetail(Long userId, UpdateUserDetailRequestDto userSignDetailRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(user.getRole() == ROLE_PARTICIPANT){
            user.updateGender(userSignDetailRequestDto.getGender());
            user.updateAge(userSignDetailRequestDto.getAge());
            user.updatePhoneNumber(userSignDetailRequestDto.getPhoneNumber());
        }

        else if(user.getRole() == ROLE_ORGANIZER){
            user.updatePhoneNumber(userSignDetailRequestDto.getPhoneNumber());
        }

        userRepository.save(user);
    }

    @Transactional
    public void signIn(UserSignInRequestDto userSignInRequestDto){

    }
}
