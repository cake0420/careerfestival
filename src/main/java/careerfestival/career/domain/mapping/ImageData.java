package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ImageData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User 프로필 이미지 1개 업로드 가능
    @Lob
    @Column(name = "user_profile_image")
    private byte[] userProfileImage;

    // 주최자 프로필 이미지 1개 업로드 가능
    @Lob
    @Column(name = "organizer_profile_image")
    private byte[] organizerProfileImage;

    // 행사 메인 이미지 1개 업로드 가능
    @Lob
    @Column(name = "event_main_image")
    private byte[] eventMainImage;

    // 행사 정보 이미지 최소 3개 업로드 하도록
    @Lob
    @Column(name = "event_inform_image")
    private List<byte[]> eventInformImage;

    // 기록장 강연/세미나 이미지 첨부 1개
    @Lob
    @Column(name = "record_lecture_seminar_image")
    private byte[] recordLectureSeminarImage;

    // 기록장 학술대회 이미지 첨부 1개
    @Lob
    @Column(name = "record_conference_image")
    private byte[] recordConferenceImage;

    // 기록장 전시/박람회 이미지 첨부 1개
    @Lob
    @Column(name = "record_exhibition_fair_image")
    private byte[] recordExhibitionFairImage;

    // 기록장 기타 이미지 첨부 1개
    @Lob
    @Column(name = "record_etc_image")
    private byte[] recordEtcImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private Organizer organizer;
}