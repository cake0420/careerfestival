package careerfestival.career.domain;

import careerfestival.career.domain.common.BaseEntity;
import careerfestival.career.domain.enums.Category;
import careerfestival.career.domain.enums.Gender;
import careerfestival.career.domain.enums.KeywordName;
import careerfestival.career.domain.mapping.NetworkDetail;
import careerfestival.career.domain.mapping.RecordDetail;
import careerfestival.career.myPage.dto.UpdateMypageResponseDto;
import careerfestival.career.record.dto.UpdateRecordResponseDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Record extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, name = "event_name")
    private String eventName;
    @Column(nullable = false, name = "event_date")
    private LocalDate eventDate;
    // 기록장에서 쓰이는 행사 타이틀 (나만의)
    @Column(length = 50, name = "event_title")
    private String eventTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<KeywordName> keywordName = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "network_detail", joinColumns = @JoinColumn(name = "network_detail_id"))
    private List<NetworkDetail> networkDetails = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "record_detail", joinColumns = @JoinColumn(name = "record_detail_id"))
    private List<RecordDetail> recordDetails = new ArrayList<>();



    //--------------------------------update--------------------------------

    public void updateCategory(Category category) {
        if(category==null) return;
        this.category = category;
    }

    public void updateeventName(String eventName) {
        if(eventName==null) return;
        this.eventName = eventName;
    }

    public void updateEventDate(LocalDate eventDate) {
        if(eventDate==null) return;
        this.eventDate = eventDate;
    }

    public void updateKeywordName(List<KeywordName> keywordName) {
        if(keywordName == null) return;
        this.keywordName = keywordName;
    }

    public void updateRecordDetails(List recordDetails) {
        if(recordDetails == null) return;
        /*this.recordDetails = recordDetails;*/
        this.recordDetails.clear(); // 기존에 저장된 레코드 디테일 모두 제거
        this.recordDetails.addAll(recordDetails); // 새로운 레코드 디테일 추가
    }

    public void updateNetworkDetails(List networkDetails) {
        if(networkDetails == null) return;
        this.networkDetails.clear(); // 기존에 저장된 네트워크 디테일 모두 제거
        this.networkDetails.addAll(networkDetails); // 새로운 네트워크 디테일 추가
    }



    /*record update 관련*/
    @Transactional
    public void update(UpdateRecordResponseDto updateRecordResponseDto) {
        this.updateCategory(updateRecordResponseDto.getCategory());
        this.updateeventName(updateRecordResponseDto.getEventName());
        this.updateEventDate(updateRecordResponseDto.getEventDate());
        this.updateKeywordName(updateRecordResponseDto.getKeywordName());
        this.updateRecordDetails(updateRecordResponseDto.getRecordDetails());
        this.updateNetworkDetails(updateRecordResponseDto.getNetworkDetails());
    }
}