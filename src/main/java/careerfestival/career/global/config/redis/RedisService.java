//package careerfestival.career.global.config.redis;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.Duration;
//
//@Service
//@RequiredArgsConstructor
////@Transactional(readOnly = true)
//public class RedisService {
//    private final RedisTemplate<String, String> redisBlackListTemplate;
//
//    public void setBlackList(String token, String value, Long timeout) {
//        redisBlackListTemplate.opsForValue()
//                .set(token, value, Duration.ofSeconds(timeout));
//    }
//
//    public String getBlackList(String token) {
//        return redisBlackListTemplate.opsForValue().get(token);
//    }
//
//    public Boolean hasTokenInBlackList(String token) {
//        return Boolean.TRUE.equals(redisBlackListTemplate.hasKey(token));
//    }
//
//    public void deleteBlackList(String token) {
//        redisBlackListTemplate.delete(token);
//    }
//}