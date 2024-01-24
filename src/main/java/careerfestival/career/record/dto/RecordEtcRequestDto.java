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
public class RecordEtcRequestDto {
    private String eventName;
    private LocalDate eventDate;
    private KeywordName keywordName;
    // 기타에만 있는 주제, 내용 칸을 위한 topic과 topicDetail
    private String topic;
    private String topicDetail;

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
                .keywordName(keywordName)
                .topic(topic)
                .topicDetail(topicDetail)
                .eventTitle(eventTitle)
                .eventDescription(eventDescription)
                .networkingName(networkingName)
                .networkingContact(networkingContact)
                .build();
    }
}
