package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.Chat;
import EST.Baemin.Manager.domain.ChatRoom;
import EST.Baemin.Manager.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomResponse {
  private long id;
  private User user1;
  private User user2;
  private List<Chat> chats;
  private String lastChat;

  public static ChatRoomResponse from(ChatRoom chatRoom) {
    ChatRoomResponse response = new ChatRoomResponse();
    response.setId(chatRoom.getId());
    response.setUser1(chatRoom.getUser1());
    response.setUser2(chatRoom.getUser2());
    response.setChats(chatRoom.getChats().stream()
                              .map(chat -> {
                                chat.setMessage(chat.getMessage().replace("\n", "<br>"));
                                return chat;
                              })
                              .toList()
    );
    if (!chatRoom.getChats().isEmpty()) {
      response.setLastChat(chatRoom.getChats().get(chatRoom.getChats().size() - 1).getMessage());
    }
    return response;
  }
}
