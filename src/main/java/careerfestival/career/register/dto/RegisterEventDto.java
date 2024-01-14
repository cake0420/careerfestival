package careerfestival.career.register.dto;

import careerfestival.career.domain.Event;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterEventDto {
    private LocalDate recruitmentStart;
    private LocalDate recruitmentEnd;

    private String eventName;
    private String description;

    // 행사 신청 외부사이트
    private String link;
    private String eventContent;

    private String managerName;
    private String managerEmail;

    @Builder
    public Event toEntity() {
        return Event.builder()
                .recruitmentStart(recruitmentStart)
                .recruitmentEnd(recruitmentEnd)
                .eventName(eventName)
                .description(description)
                .link(link)
                .eventContent(eventContent)
                .managerName(managerName)
                .managerEmail(managerEmail)
                .build();
    }

}
