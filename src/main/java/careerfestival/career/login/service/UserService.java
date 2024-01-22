package careerfestival.career.login.service;

import careerfestival.career.domain.User;
import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.domain.mapping.Region;
import careerfestival.career.login.dto.CustomUserDetails;
import careerfestival.career.jwt.JWTUtil;
import careerfestival.career.myPage.dto.MyPageResponseDto;
import careerfestival.career.myPage.dto.UpdateMypageResponseDto;
import careerfestival.career.login.dto.UserSignUpRequestDto;
import careerfestival.career.repository.RegionRepository;
import careerfestival.career.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RegionRepository regionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JWTUtil jwtUtil;

    @Transactional
    public String signUp(UserSignUpRequestDto userSignUpRequestDto) {

        // DB에 존재하는지 여부 (email로 판단)
        boolean exists = userRepository.existsByEmail(userSignUpRequestDto.getEmail());
        if(exists){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        //비밀번호 입력 확인
        if(!userSignUpRequestDto.getPassword().equals(userSignUpRequestDto.getCheckPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }



        User user = userSignUpRequestDto.toEntity();
        user.updatePassword(bCryptPasswordEncoder.encode(userSignUpRequestDto.getPassword()));

        userRepository.save(user);

        String joinJwt = jwtUtil.createJwt(user.getId(), user.getEmail(), String.valueOf(user.getRole()), 600000L);

        return joinJwt;
    }


    @Transactional
    public void findUserByEmailAndUpdate(String email, UpdateMypageResponseDto updateMypageResponseDto){
        User findUser = userRepository.findByEmail(email);
        findUser.update(updateMypageResponseDto);
        String city = updateMypageResponseDto.getCity();
        String addressLine = updateMypageResponseDto.getAddressLine();
        if(city == null || addressLine == null){
            return;
        }
        if (regionRepository.findRegionByCityAndAddressLine(city, addressLine) == null) {
            return;
        }
        findUser.updateAddressLine(addressLine);
    }

    @Transactional
    public User findUserByCustomUserDetails(CustomUserDetails customUserDetails){
        return userRepository.findByEmail(customUserDetails.getUsername());
    }

    @Transactional
    public MyPageResponseDto fillMyPage(User user) {
        MyPageResponseDto myPageResponseDto = new MyPageResponseDto();
        myPageResponseDto.setName(user.getName());
        myPageResponseDto.setEmail(user.getEmail());
        myPageResponseDto.setAge(user.getAge());
        myPageResponseDto.setGender(user.getGender());
        myPageResponseDto.setPhoneNumber(user.getPhoneNumber());
        myPageResponseDto.setCompany(user.getCompany());
        myPageResponseDto.setDepartment(user.getDepartment());
        myPageResponseDto.setPosition(user.getPosition());
        myPageResponseDto.setAddressLine(user.getAddressLine());
        List<KeywordName> keyword = user.getKeyword();
        myPageResponseDto.setKeywordName(keyword);
        return myPageResponseDto;
    }

}
