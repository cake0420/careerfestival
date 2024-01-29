package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Record;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class RecordDetail {

    // 세션 이름 & 부스 이름 일괄처리
    private String detailRecordName;
    // 해당 항목에 대한 설명 일괄처리
    private String recordDescription;
    // 설명에 대한 이미지 저장 경로
    private String descriptionFileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Record record;
}