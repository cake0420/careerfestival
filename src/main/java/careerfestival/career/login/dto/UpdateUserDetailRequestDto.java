package careerfestival.career.login.dto;

import careerfestival.career.domain.enums.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDetailRequestDto {
    private Gender gender;
    private int age;
    private String phoneNumber;

    //관심 지역

    //커리어 키워드

    //소속
}