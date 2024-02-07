package careerfestival.career.eventPage.dto;

import careerfestival.career.domain.Event;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventPageResponseDto {
    private Long eventId;
    private String eventName;
    private LocalDateTime recruitmentStart;
    private LocalDateTime recruitmentEnd;
    private String eventCost;

    public static EventPageResponseDto fromEntity(Event event){
        return EventPageResponseDto.builder()
                .eventId(event.getId())
                .eventName(event.getEventName())
                .recruitmentStart(event.getRecruitmentStart())
                .recruitmentEnd(event.getRecruitmentEnd())
                .eventCost(event.getEventCost())
                .build();
    }
}
