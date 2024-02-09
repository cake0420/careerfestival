package careerfestival.career.subscribe.dto;


import careerfestival.career.domain.User;
import careerfestival.career.domain.mapping.Subscribe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor


public class SubscribeRequestDto {

    private String fromUser;
    @Builder
    public static SubscribeRequestDto of(String fromUser) {
        return SubscribeRequestDto.builder()
                .fromUser(fromUser)
                .build();
    }


}
