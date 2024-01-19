package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.Record;
import careerfestival.career.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Lob
    @Column(name = "user_profile_image")
    private byte[] userProfileImage;

    @Lob
    @Column(name = "organizer_profile_imaage")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;
}
