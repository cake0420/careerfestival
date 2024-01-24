package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "imagedata")
public class ImageData extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User 프로필 이미지 1개 업로드 가능
    @Column(name = "user_file_url")
    private String userFileUrl;

    @Column(name = "organizer_file_url")
    private String organizerFileUrl;

    @Column(name = "event_main_file_url")
    private String eventMainFileUrl;

    // 행사 정보 이미지 최소 3개 업로드 하도록
    @Column(name = "event_inform_file_url")
    private String eventInformFileUrl;

    // 기록장 강연/세미나 이미지 첨부 1개
    @Column(name = "record_lecture_seminar_file_url")
    private String recordLectureSeminarFileUrl;

    // 기록장 학술대회 이미지 첨부 1개
    @Column(name = "record_conference_file_url")
    private String recordConferenceFileUrl;

    // 기록장 전시/박람회 이미지 첨부 1개
    @Column(name = "record_exhibition_fair_file_url")
    private String recordExhibitionFairFileUrl;

    @Column(name = "record_etc_file_url")
    private String recordEtcFileUrl;

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