package careerfestival.career.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class EventAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //행사 주소
    @Column(nullable = false, length = 40, name ="ADDRESS")
    private String address;
    @Column(nullable = false, length = 40, name ="SPECADDRESS")
    private String specAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    private Event event;
}
