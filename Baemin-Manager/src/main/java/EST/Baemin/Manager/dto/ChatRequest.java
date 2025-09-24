package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ChatRequest {
  private long id;
  private long chatRoomId;
  private long senderId;
  private String message;
  private Date createdAt;

  @Builder
  public ChatRequest(long id, long chatRoomId, long senderId, String message) {
    this.id = id;
    this.chatRoomId = chatRoomId;
    this.senderId = senderId;
    this.message = message;
  }

  public Chat toEntity() {
    return Chat.builder()
                   .chatRoomId(this.chatRoomId)
                   .senderId(this.senderId)
                   .message(this.message)
                   .createdAt(this.createdAt)
                   .build();
  }
}