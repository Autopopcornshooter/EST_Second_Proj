package EST.Baemin.Manager.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    // 발신자
    @Column(nullable = false)
    private Long senderId;

    // 메세지
    @Column(length = 1000, nullable = false)
    private String message;

    // 전송 시간
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 확인 여부
    @Column(nullable = false)
    private Boolean isRead;
}
