package EST.Baemin.Manager.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)  // 들어간 값 수정 X
    private Long id;    // restaurant_id

    // 우선은 Null로 진행, 유저의 데이터 정보에서 가게 정보를 빼와야 사용 가능
//    @Column(nullable = false)
    private String name;    // 가게명

    @Column(nullable = false)
    private String mainMenu;    // 대표메뉴

    @Column(length = 500)   // 500자 까지
    private String description; // 대표메뉴 설명

    @Column(nullable = false)
    private String address; // 주소

    @Column(nullable = false)
    private Integer price;  // 가격

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private Integer view = 0; // 조회수 초기값은 0

    private String imageUrl;    // 이미지

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();
}
