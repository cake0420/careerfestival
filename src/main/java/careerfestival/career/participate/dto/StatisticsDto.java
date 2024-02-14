package careerfestival.career.participate.dto;

import careerfestival.career.domain.Event;
import careerfestival.career.mainPage.dto.MainPageResponseDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatisticsDto {

    private int countedMale;
    private int countedFemale;

    private int countedUnder19;
    private int counted20to24;
    private int counted25to29;
    private int counted30to34;
    private int counted35to39;
    private int countedOver40;

    private int countedCompanyType1;
    private int countedCompanyType2;
    private int countedCompanyType3;
    private int countedCompanyType4;
    private int countedCompanyType5;
    private int countedCompanyType6;

    public static StatisticsDto fromEntity(Event event) {
        return StatisticsDto.builder()
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
