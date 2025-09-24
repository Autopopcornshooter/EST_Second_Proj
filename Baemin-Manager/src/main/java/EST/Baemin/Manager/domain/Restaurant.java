package EST.Baemin.Manager.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 가게 ID
    @Column(updatable = false)
    private Long id;
    // 가게명
    @Column(nullable = false)
    private String name;
    // 소개
    @Column(length = 500, nullable = false)
    private String description;
    // 주소
    @Column(nullable = false)
    private String address;
    // 전화번호
    @Column(nullable = false)
    private String phone;
    // 등록일
    @Column(nullable = false)
    private LocalDate createAt;
    // 사진 ID
    @Column(nullable = false)
    private Long photo;

}
