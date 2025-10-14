package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.Chat;
import EST.Baemin.Manager.domain.ChatRoom;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRequest {

  private Long senderId;
  private String message;
  private Long chatRoomId; // ChatRoom 참조용

  @Builder
  public ChatRequest(Long senderId, String message, Long chatRoomId) {
    this.senderId = senderId;
    this.message = message;
    this.chatRoomId = chatRoomId;
  }

  // Chat 엔티티 변환
  public Chat toEntity(ChatRoom chatRoom) {
    Chat chat = Chat.builder()
                        .senderId(this.senderId)
                        .message(this.message)
                        .build();
    chat.setChatRoom(chatRoom); // 양방향 연결
    return chat;
  }
}