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
  @Column(name = "chat_room_id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user1_id", nullable = false)
  private User user1;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user2_id", nullable = false)
  private User user2;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  @OrderBy("createdAt ASC")
  private List<Chat> chats = new ArrayList<>();

  @Builder
  public ChatRoom(User user1, User user2) {
    this.user1 = user1;
    this.user2 = user2;
  }
}
