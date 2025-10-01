package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.Chat;
import EST.Baemin.Manager.domain.ChatRoom;
import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.dto.ChatRequest;
import EST.Baemin.Manager.dto.ChatRoomResponse;
import EST.Baemin.Manager.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ChatController {

  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  // 채팅방 조회, 없으면 생성
  @PostMapping("/api/chat/start/{otherUserId}")
  public String startChat(@PathVariable Long otherUserId,
                          Authentication authentication,
                          RedirectAttributes redirectAttributes) {
    User user = (User) authentication.getPrincipal();

    Long roomId = chatService.getOrCreateChatRoom(user.getId(), otherUserId);
    redirectAttributes.addFlashAttribute("roomId", roomId);

    return "redirect:/chat";
  }

  // 채팅 페이지
  @GetMapping("/chat")
  public String chatPage(@RequestParam(required = false) Long roomId,
                         Model model,
                         Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    model.addAttribute("loginUser", user);

    List<ChatRoomResponse> chatRooms = chatService.getChatRoomsByUserId(user.getId());
    model.addAttribute("chatRooms", chatRooms);

    if (roomId != null) {
      model.addAttribute("selectedRoom", chatService.findById(roomId));
    } else {
      model.addAttribute("selectedRoom", null);
    }

    return "chat";
  }

  // 초기 채팅 내용 조회 (AJAX용, 화면 렌더링)
  @GetMapping("/api/chat/{roomId}")
  public String getChatRoom(@PathVariable Long roomId, Model model,
                            Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    model.addAttribute("loginUser", user);

    ChatRoomResponse chatRoom = chatService.findById(roomId);
    model.addAttribute("selectedRoom", chatRoom);

    return "chat :: chatFragment"; // Thymeleaf fragment
  }

}
