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
<<<<<<< HEAD
    private List<String> detailEventName;
    private List<String> eventDescription;
    private List<String> networkingName;
    private List<String> networkingContact;

    @Builder
    public Record toEntity() {
        return Record.builder()
                .eventName(eventName)
                .eventDate(eventDate)
                .keywordName(Collections.singletonList(keywordName))
                .detailEventName(detailEventName.toString())
                .eventDescription(eventDescription.toString())
                .networkingName(networkingName.toString())
                .networkingContact(networkingContact.toString())
                .build();
    }
}