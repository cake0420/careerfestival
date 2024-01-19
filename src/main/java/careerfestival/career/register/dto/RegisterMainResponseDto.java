package careerfestival.career.register.dto;

import careerfestival.career.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterMainResponseDto {
    private String eventName;
    private String description;

    public static RegisterMainResponseDto fromEntity(Event event) {
        return RegisterMainResponseDto.builder()
                .eventName(event.getEventName())
                .description(event.getDescription())
                .build();
    }
}
