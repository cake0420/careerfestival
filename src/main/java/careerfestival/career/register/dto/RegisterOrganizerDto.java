package careerfestival.career.register.dto;

import careerfestival.career.domain.mapping.Organizer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterOrganizerDto {
    private String organizerName;
    // 주최자 프로필 이미지 업로드 만들기

    @Builder
    public Organizer toEntity() {
        return Organizer.builder()
                .organizerName(organizerName)
                .build();
    }
}
