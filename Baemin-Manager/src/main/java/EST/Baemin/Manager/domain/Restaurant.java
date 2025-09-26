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

    @Column(nullable = false)
    private String name;    // 가게명

    @Column(nullable = false)
    private String mainMenu;    // 대표메뉴

    @Column(length = 500)   // 500자 까지
    private String description; // 대표메뉴 설명

    @Column(nullable = false)
    private String address; // 주소

    @Column(nullable = false)
    private Integer price;  // 가격

    private String imageUrl;    // 이미지

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

}
