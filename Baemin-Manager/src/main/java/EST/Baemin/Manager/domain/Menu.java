package EST.Baemin.Manager.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 메뉴 ID
    @Column(updatable = false)
    private Long id;

    // 카테고리
    @Column(nullable = false)
    private Long category;

    // 사진ID
    @Column(nullable = false)
    private Long photo;

    // 메뉴 명
    @Column(nullable = false)
    private String name;

    // 가격
    @Column(nullable = false)
    private Integer price;

    // 조회수
    @Column(nullable = false)
    private Integer views;

    // 수정 시간
    @Column(nullable = false)
    private LocalDate updates;

    // 삭제 시간
    @Column(nullable = false)
    private LocalDate delete;

}
