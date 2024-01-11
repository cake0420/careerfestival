package careerfestival.career.user.service;

import careerfestival.career.domain.Role;
import careerfestival.career.domain.User;
import careerfestival.career.exception.CustomException;
import careerfestival.career.exception.ErrorCode;
//import careerfestival.career.global.config.JwtTokenProvider;
//import careerfestival.career.global.config.redis.RedisService;
import careerfestival.career.repository.UserRepository;
import careerfestival.career.user.dto.request.EmailDto;
import careerfestival.career.user.dto.request.LoginDto;
import careerfestival.career.user.dto.response.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;
//    private final RedisService redisService;

    public ResponseEntity<Void> checkLoginId(LoginDto loginIdDto) {
        userRepository.findByLoginId(loginIdDto.getLoginId())
                .ifPresent(user -> {
                    throw new CustomException(ErrorCode.LOGIN_ID_DUPLICATED, "이미 존재하는 ID");
                });

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> checkEmail(EmailDto emailDto) {
        userRepository.findByEmail(emailDto.getEmail())
                .ifPresent(user -> {
                    throw new CustomException(ErrorCode.EMAIL_DUPLICATED, "이미 존재하는 email");
                });
        return ResponseEntity.ok().build();
    }

//    public LoginResponseDto login(LoginDto loginDto) {
//        //loginId 확인
//        User user = userRepository.findByLoginId(loginDto.getLoginId())
//                .orElseThrow(() -> new CustomException(ErrorCode.ID_NOT_FOUND, "존재하지 않는 ID"));
//
//        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
//            throw new CustomException(ErrorCode.INVALID_PASSWORD, "잘못된 비밀번호");
//        }
//
////        String accessToken = jwtTokenProvider.createAccessToken(loginDto.getLoginId(), Role.USER.name());
//
//        return new LoginResponseDto(user, accessToken);
//    }
//
//    public ResponseEntity<Void> logout(String token) {
//        // access token의 유효시간 가져와서 블랙리스트 등록
//        Long expirationTime = jwtTokenProvider.getExpiration(token);
//        redisService.setBlackList(token, "logout", expirationTime / 1000);
//        return ResponseEntity.ok().build();
//    }
}
