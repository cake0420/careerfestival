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
public class MainPageFestivalListResponseDto {
    private String eventMainFileUrl;
    private String eventName;
    private LocalDateTime recruitmentStart;
    private LocalDateTime recruitmentEnd;
    private String eventCost;

    public static MainPageFestivalListResponseDto fromEntity(Event event){
        return MainPageFestivalListResponseDto.builder()
                .eventMainFileUrl(event.getEventMainFileUrl())
                .eventName(event.getEventName())
                .recruitmentStart(event.getRecruitmentStart())
                .recruitmentEnd(event.getRecruitmentEnd())
                .build();
    }

    public static MainPageResponseDto fromEntityName(Event event) {
        return MainPageResponseDto.builder()
                .eventName(event.getEventName())
                .build();
    }
}
