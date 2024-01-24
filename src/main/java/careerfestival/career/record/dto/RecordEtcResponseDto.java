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
public class RecordEtcResponseDto {
    private String eventName;
    private LocalDate eventDate;
    private List<KeywordName> keywordName;
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
    public static RecordEtcResponseDto fromEntity(Record record){
        return RecordEtcResponseDto.builder()
                .eventName(record.getEventName())
                .eventTitle(record.getEventTitle())
                .eventDate(record.getEventDate())
                .keywordName(record.getKeywordName())
                .topic(record.getTopic())
                .topicDetail(record.getTopicDetail())
                .eventDescription(record.getEventDescription())
                .networkingName(record.getNetworkingName())
                .networkingContact(record.getNetworkingContact())
                .build();
    }
}
