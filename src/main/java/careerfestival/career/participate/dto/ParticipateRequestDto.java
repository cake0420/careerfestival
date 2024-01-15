package careerfestival.career.participate.dto;

import careerfestival.career.domain.Comment;
import careerfestival.career.domain.Event;
import careerfestival.career.domain.mapping.Participate;
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
