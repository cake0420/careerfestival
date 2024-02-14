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
    private String eventMainFileUrl;
    private String eventInformFileUrl;
    private LocalDateTime recruitmentStart;
    private LocalDateTime recruitmentEnd;
    private String eventCost;

    private int countedMale;
    private int countedFemale;

    private int countedUnder19 ;
    private int counted20to24 ;
    private int counted25to29 ;
    private int counted30to34 ;
    private int counted35to39;
    private int countedOver40;

    private int countedCompanyType1;
    private int countedCompanyType2;
    private int countedCompanyType3 ;
    private int countedCompanyType4;
    private int countedCompanyType5;
    private int countedCompanyType6 ;

    public static EventPageResponseDto fromEntity(Event event){
        return EventPageResponseDto.builder()
                .eventId(event.getId())
                .eventName(event.getEventName())
                .eventMainFileUrl(event.getEventMainFileUrl())
                .eventInformFileUrl(event.getEventInformFileUrl())
                .recruitmentStart(event.getRecruitmentStart())
                .recruitmentEnd(event.getRecruitmentEnd())
                .eventCost(event.getEventCost())

                .countedMale(event.getCountedMale())
                .countedFemale(event.getCountedFemale())
                .countedUnder19(event.getCountedUnder19())
                .counted20to24(event.getCounted20to24())
                .counted25to29(event.getCounted25to29())
                .counted30to34(event.getCounted30to34())
                .counted35to39(event.getCounted35to39())
                .countedOver40(event.getCountedOver40())
                .countedCompanyType1(event.getCountedCompanyType1())
                .countedCompanyType2(event.getCountedCompanyType2())
                .countedCompanyType3(event.getCountedCompanyType3())
                .countedCompanyType4(event.getCountedCompanyType4())
                .countedCompanyType5(event.getCountedCompanyType5())
                .countedCompanyType6(event.getCountedCompanyType6())
                .build();
    }
}
