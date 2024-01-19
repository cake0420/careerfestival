package careerfestival.career.record.dto;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.enums.KeywordName;
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
public class RecordMainResponseDto {
    private String eventName;
    private LocalDate eventDate;
    private String eventTitle;
    private String eventDescription;
    private List<KeywordName> keywordName;

    public static RecordMainResponseDto fromEntity(Record record) {
        return RecordMainResponseDto.builder()
                .eventName(record.getEventName())
                .eventDate(record.getEventDate())
                .eventTitle(record.getEventTitle())
                .eventDescription(record.getEventDescription())
                .keywordName(record.getKeywordName())
                .build();
    }
}
