package EST.Baemin.Manager.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Long user1Id;

  @Column(nullable = false)
  private Long user2Id;

//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user1_id", nullable = false)
//  private User user1;
//
//  @ManyToOne(fetch = FetchType.LAZY)
//  @JoinColumn(name = "user2_id", nullable = false)
//  private User user2;

  @UpdateTimestamp
  @Column(nullable = false)
  private LocalDateTime updatedAt;

  // 양방향: ChatRoom → Chat, LAZY 로딩
  @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @OrderBy("createdAt ASC")
  private List<Chat> chats = new ArrayList<>();

  @Builder
  public ChatRoom(Long user1Id, Long user2Id) {
    this.user1Id = user1Id;
    this.user2Id = user2Id;
  }
}
