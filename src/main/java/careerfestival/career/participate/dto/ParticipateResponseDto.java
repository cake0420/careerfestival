package careerfestival.career.participate.dto;

import careerfestival.career.domain.mapping.Participate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipateResponseDto {
    private Long id;
    private String email;
    private String eventName;

    public ParticipateResponseDto(Participate participate) {
        this.id = participate.getId();
        this.email = participate.getUser().getEmail();
        this.eventName = participate.getEvent().getEventName();
    }
}
