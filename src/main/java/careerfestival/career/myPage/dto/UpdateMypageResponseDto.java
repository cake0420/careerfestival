package careerfestival.career.myPage.dto;

import careerfestival.career.domain.enums.Gender;
import careerfestival.career.domain.enums.KeywordName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMypageResponseDto {
    private String name;

    private String email;

    private Gender gender;

    private int age;

    private String phoneNumber;

    //소속, 부서/학과, 직급
    private String company;
    private String department;
    private String position;

    //커리어 키워드
    private KeywordName keywordName;

    //관심 지역
}
