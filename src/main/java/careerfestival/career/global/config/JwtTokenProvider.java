//package careerfestival.career.global.config;
//
//import careerfestival.career.global.config.redis.RedisService;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Base64;
//import java.util.Date;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtTokenProvider {
//    @Value("${jwt.secretkey}")
//    private String secretKey;
//    SecretKey key;
//
//    @Value("${jwt.tokenValidTime}")
//    private long tokenValidTime;
//
//    private final CustomUserDetailsService userDetailsService;
//
//    @Autowired
//    private final RedisService redisService;
//
//    // 객체 초기화, secretKey -> Base64로 인코딩
//    @PostConstruct
//    protected void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//        key = Keys.hmacShaKeyFor(secretKey.getBytes());
//    }
//
//    // JWT 토큰 설명
//    public String createAccessToken(String loginId, String role) {
//        Claims claims = Jwts.claims().setSubject(loginId);
//        claims.put("role", role);
//        Date now = new Date();
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + tokenValidTime))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // public String refreshToken()
//
//    // Header에서 TOKEN 꺼내기
//    public String resolveToken(HttpServletRequest request){
//        return request.getHeader(HttpHeaders.AUTHORIZATION);
//    }
//
//    //Token 유효성 - 만료 여부 검증
//    // redis 로직 추가하기!!! - 블랙리스트에 있는지
//    public boolean validateAccessToken(String token) {
////        System.out.println("[JwtTokenProvider] validateAccessToken 1. 토큰 들어옴 ");
//        try {
////            System.out.println("[JwtTokenProvider] validateAccessToken 2. try문");
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            if (redisService.hasTokenInBlackList(token)) {
//                System.out.println("[JwtTokenProvider] validateAccessToken 레디스에 있는 토큰");
////                throw new CustomException(ErrorCode.INVALID_TOKEN, "로그아웃된 ACCESS TOKEN");
//                return false;
//            }
//            return true;
//        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
//            log.info(e.toString());
//            log.info("잘못된 JWT 서명");
//        } catch (ExpiredJwtException e) {
//            log.info(e.toString());
//            log.info("만료된 JWT 토큰");
//        } catch (UnsupportedJwtException e) {
//            log.info(e.toString());
//            log.info("지원되지 않는 JWT 토큰");
//        } catch (IllegalArgumentException e) {
//            log.info(e.toString());
//            log.info("잘못된 JWT 토큰");
//        }
//        return false;
//    }
//
//    public boolean validateToken(String token) {
//        Date expiration = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getExpiration();
//        boolean isExpired = expiration.before(new Date());
//
//        return isExpired;
//    }
//
//    // Token에서 사용자 정보 꺼내기
//    public String getLoginId(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    // 권한 부여
//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getLoginId(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
//
//    public Long getExpiration(String token) {
//        Date expiration = Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getExpiration();
//        long now = new Date().getTime();
//
//        return expiration.getTime() - now;
//    }
//}
