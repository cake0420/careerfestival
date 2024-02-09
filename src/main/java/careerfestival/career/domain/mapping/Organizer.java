package careerfestival.career.domain.mapping;

import careerfestival.career.AES.AESUtil;
import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Organizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 300, name = "organizer_name")
    private String organizerName;

    @Column(name = "organizer_profile_file_url")
    private String organizerProfileFileUrl;

    @Column(columnDefinition = "INT DEFAULT 0", name = "count_event")
    private int countEvent;

    @Column(name = "encrypted_email")
    private String encryptedEmail;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private List<Event> event = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscribe> subscribe = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateCountEvent() {
        this.countEvent += 1;
    }

    public void setOrganizerEmail(String encryptedEmail) {
        this.encryptedEmail = AESUtil.encrypt(encryptedEmail);
    }
}