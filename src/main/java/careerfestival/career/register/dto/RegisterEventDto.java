package careerfestival.career.register.dto;

import careerfestival.career.domain.Event;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterEventDto {
    private String eventName;
    private String description;

    @Builder
    public Event toEntity() {
        return Event.builder()
                .eventName(eventName)
                .description(description)
                .build();
    }
}
