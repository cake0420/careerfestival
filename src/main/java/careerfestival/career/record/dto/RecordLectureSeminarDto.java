package careerfestival.career.record.dto;

import careerfestival.career.domain.Record;
import careerfestival.career.domain.enums.Category;
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
public class RecordLectureSeminarDto {
    private Category category;
    private String eventName;
    private LocalDate eventDate;
    private List<KeywordName> keywordName;
    private String eventTitle;
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
                .eventDate(eventDate)
                .eventDescription(eventDescription)
                .eventTitle(eventTitle)
                .networkingName(networkingName)
                .networkingContact(networkingContact)
                .build();
    }

}
