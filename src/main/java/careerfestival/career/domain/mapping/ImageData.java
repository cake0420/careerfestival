package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Event;
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

    @Lob
    @Column(name = "user_profile_image")
    private byte[] userProfileImage;

    @Lob
    @Column(name = "organizer_profile_image")
    private byte[] organizerProfileImage;

    @Lob
    @Column(name = "event_main_image")
    private byte[] eventMainImage;

    @Lob
    @Column(name = "event_inform_image")
    private List<byte[]> eventInformImage;

    @Lob
    @Column(name = "register_lecture_seminar_image")
    private List<byte[]> registerLectureSeminarImage;

    @Lob
    @Column(name = "register_conference_image")
    private List<byte[]> registerConferenceImage;

    @Lob
    @Column(name = "register_exhibition_fair_image")
    private List<byte[]> registerExhibitionFairImage;

    @Lob
    @Column(name = "register_etc_image")
    private List<byte[]> registerEtcImage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
