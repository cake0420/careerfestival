package careerfestival.career.record.dto;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.mapping.RecordKeyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 강연, 세미나 DTO
public class RecordLectureSeminarDto {

    private LocalDateTime eventDate;

    private String eventName;
    private List<RecordKeyword> recordKeyword;

    private String networkingName;
    private String networkingContact;

    @Builder
    public Record toEntity() {
        return Record.builder()
                .eventName(eventName)
                .eventDate(eventDate)
                .recordKeywords(recordKeyword)
                .networkingName(networkingName)
                .networkingContact(networkingContact)
                .build();
    }
}