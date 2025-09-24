package EST.Baemin.Manager.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor

public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    // 주소
    @Column(nullable = false)
    private String address;

    // 위도
    @Column(nullable = false)
    private Double latitude;

    // 경도
    @Column(nullable = false)
    private Double longitude;

    // 구글 placeId
    @Column(nullable = false)
    private String google_place_id;
}
