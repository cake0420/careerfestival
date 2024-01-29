package careerfestival.career.domain.mapping;

import careerfestival.career.domain.Event;
import careerfestival.career.domain.User;
import careerfestival.career.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "city")
    private String city;

    @Column(nullable = false, name = "address_line")
    private String addressLine;

    @OneToOne(mappedBy = "region", cascade = CascadeType.ALL)
    private Event event;

    @OneToOne(mappedBy = "region", cascade = CascadeType.ALL)
    private User user;
}