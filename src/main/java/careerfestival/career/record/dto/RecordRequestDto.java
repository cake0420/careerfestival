package careerfestival.career.record.dto;

import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.domain.Record;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordRequestDto {
    private String eventName;
    private LocalDate eventDate;
    private KeywordName keywordName;
    private String detailEventName;
    private String eventDescription;
    private String networkingName;
    private String networkingContact;

    @Builder
    public Record toEntity() {
    return Record.builder()
            .eventName(eventName)
            .eventDate(eventDate)
            .keywordName(Collections.singletonList(keywordName))
            .detailEventName(detailEventName)
            .eventDescription(eventDescription)
            .networkingName(networkingName)
            .networkingContact(networkingContact)
            .build();

    }
}
