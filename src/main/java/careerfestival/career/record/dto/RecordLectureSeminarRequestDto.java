package careerfestival.career.record.dto;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.domain.mapping.RecordDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 강연, 세미나 DTO
public class RecordLectureSeminarRequestDto {
    private String eventName;
    private LocalDate eventDate;
    private String eventTitle;
    private KeywordName keywordName;
    private String eventDescription;
    private List<RecordDetail> recordDetails;

    @Builder
    public Record toEntity() {
        return Record.builder()
                .eventName(eventName)
                .eventTitle(eventTitle)
                .eventDate(eventDate)
                .keywordName(keywordName)
                .eventDescription(eventDescription)
                .recordDetails(recordDetails)
                .build();
    }
}
