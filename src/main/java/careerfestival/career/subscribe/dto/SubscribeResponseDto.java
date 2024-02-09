package careerfestival.career.subscribe.dto;


import careerfestival.career.domain.mapping.Subscribe;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscribeResponseDto {
    private Long toUser; // You may add more fields as needed
    private Long subscribedOrganizer;
    public SubscribeResponseDto(Subscribe subscribe) {
        this.toUser = subscribe.getToUser(); // Adjust this based on your Subscribe entity
        this.subscribedOrganizer = subscribe.getSubscribedOrganizer();
    }

}
