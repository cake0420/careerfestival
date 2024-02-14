package careerfestival.career.participate.dto;

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
}
