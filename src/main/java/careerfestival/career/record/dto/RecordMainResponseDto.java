package careerfestival.career.record.dto;

import careerfestival.career.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordMainResponseDto {
    private String eventName;

    public static RecordMainResponseDto fromEntity(Record record) {
        return RecordMainResponseDto.builder()
                .eventName(record.getEventName())
                .build();
    }
}
