package careerfestival.career.mainPage.dto;

import careerfestival.career.domain.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainPageResponseDto {
    private String eventName;
    private LocalDateTime recruitmentStart;
    private LocalDateTime recruitmentEnd;
    private boolean favorites;
    private String eventCost;

    public static MainPageResponseDto fromEntity(Event event) {
        return MainPageResponseDto.builder()
                .eventName(event.getEventName())
                .recruitmentStart(event.getRecruitmentStart())
                .recruitmentEnd(event.getRecruitmentEnd())
                .favorites(event.isFavorites())
                .eventCost(event.getEventCost())
                .build();
    }
}
