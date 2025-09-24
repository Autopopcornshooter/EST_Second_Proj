package EST.Baemin.Manager.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private long chatRoomId;

  @Column
  private long senderId;

  @Column
  private String message;

  @Column
  private Date createdAt;

  @Builder
  public Chat(long chatRoomId, long senderId, String message, Date createdAt) {
    this.chatRoomId = chatRoomId;
    this.senderId = senderId;
    this.message = message;
    this.createdAt = createdAt;
  }
}
