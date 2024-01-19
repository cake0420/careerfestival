package careerfestival.career.register.dto;

import careerfestival.career.domain.Event;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterEventDto {
    private LocalDateTime recruitmentStart;
    private LocalDateTime recruitmentEnd;

    private String eventName;
    private String description;

    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;

    // 행사 신청 외부사이트
    private String link;
    private String eventContent;

    private String eventCost;

    private String address;
    private String specAddress;

    private String managerName;
    private String managerEmail;

    private String eventEtcDetail;

    @Builder
    public Event toEntity() {
        return Event.builder()
                .recruitmentStart(recruitmentStart)
                .recruitmentEnd(recruitmentEnd)
                .eventName(eventName)
                .description(description)
                .eventStart(eventStart)
                .eventEnd(eventEnd)
                .link(link)
                .eventContent(eventContent)
                .eventCost(eventCost)
                .address(address)
                .specAddress(specAddress)
                .managerName(managerName)
                .managerEmail(managerEmail)
                .eventEtcDetail(eventEtcDetail)
                .build();
    }



}
