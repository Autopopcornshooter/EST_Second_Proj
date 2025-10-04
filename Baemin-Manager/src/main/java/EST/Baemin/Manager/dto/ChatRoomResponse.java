package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.Chat;
import EST.Baemin.Manager.domain.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class
ChatRoomResponse {
  private long id;
  private long user1Id;
  private long user2Id;
  private LocalDateTime updatedAt;
  private List<Chat> chats;

  public ChatRoomResponse(ChatRoom chatRoom) {
    this.id = chatRoom.getId();
    this.user1Id = chatRoom.getUser1Id();
    this.user2Id = chatRoom.getUser2Id();
    this.updatedAt = chatRoom.getUpdatedAt();
    this.chats = chatRoom.getChats();
  }
}
