package EST.Baemin.Manager.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private long user1Id;

  @Column
  private long user2Id;

  @Column
  private Date updatedAt;

  @OneToMany(mappedBy = "chatRoomId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Chat> chats;

  @Builder
  public ChatRoom(long user1Id, long user2Id, Date updatedAt) {
    this.user1Id = user1Id;
    this.user2Id = user2Id;
    this.updatedAt = updatedAt;
  }
}
