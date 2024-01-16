package careerfestival.career.participate.dto;

import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ParticipateRequestDto {

    private Long id;
    private String email;
    private String eventName;

}
