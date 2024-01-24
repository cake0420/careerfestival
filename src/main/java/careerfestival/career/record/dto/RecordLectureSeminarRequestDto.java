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
// 강연, 세미나 DTO
public class RecordLectureSeminarRequestDto {
    private String eventName;
    private LocalDate eventDate;
    private String eventTitle;
    private KeywordName keywordName;
    private String eventDescription;
    /*
    ----이미지 첨부 관련 내용----
     */
    private String networkingName;
    private String networkingContact;

    @Builder
    public Record toEntity() {
        return Record.builder()
                .eventName(eventName)
                .eventTitle(eventTitle)
                .eventDate(eventDate)
                .keywordName(keywordName)
                .eventDescription(eventDescription)
                .networkingName(networkingName)
                .networkingContact(networkingContact)
                .build();
    }
}
