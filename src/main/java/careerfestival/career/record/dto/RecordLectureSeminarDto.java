package careerfestival.career.record.dto;

import careerfestival.career.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordLectureSeminarDto {
    private LocalDateTime eventDate;
    private String networkingName;
    private String networkingContact;

    @Builder
    public Record toEntity() {
        return Record.builder()
                .eventDate(eventDate)
                .networkingName(networkingName)
                .networkingContact(networkingContact)
                .build();
    }
}
