package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.ChatRoom;
import EST.Baemin.Manager.dto.ChatRequest;
import EST.Baemin.Manager.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ChatController {
  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }
  @GetMapping("/api/chat")
  public String getChatRooms(Long userId, Model model) {
    List<ChatRoom> chatRooms = chatService.getChatRoomsByUserId(userId);
    model.addAttribute("chatRoom", chatRooms);
    return "chat";
  }

  @PostMapping("/api/chat")
  public String addChat(ChatRequest request) {
    chatService.savaChat(request);
    return "redirect:/chat";
  }

}
