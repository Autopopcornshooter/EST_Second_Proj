package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.Chat;
import EST.Baemin.Manager.domain.ChatRoom;
import EST.Baemin.Manager.dto.ChatRequest;
import EST.Baemin.Manager.service.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
  private final ChatService chatService;

  public ChatWebSocketController(ChatService chatService) {
    this.chatService = chatService;
  }

  @MessageMapping("/chat/{roomId}")
  @SendTo("/topic/{roomId}")
  public ChatRequest sendToRoom(@DestinationVariable Long roomId, @Payload ChatRequest chatRequest) {
    Chat chat = chatService.saveChat(chatRequest);
    return chatRequest;
  }

}
