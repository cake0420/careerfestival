package careerfestival.career.subscribe.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor


public class SubscribeRequestDto {

    private Long toUser;
    private Long subscribedOrganizer;
    private Integer subscriberCount;
    @Builder
    public static SubscribeRequestDto of(Long toUser, Long subscribedOrganizer, Integer subscriberCount) {
        return SubscribeRequestDto.builder()
                .toUser(toUser)
                .subscribedOrganizer(subscribedOrganizer)
                .subscriberCount(subscriberCount)
                .build();
    }
}
