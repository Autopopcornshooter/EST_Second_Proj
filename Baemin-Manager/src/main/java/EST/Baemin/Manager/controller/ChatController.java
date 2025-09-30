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

    // 채팅방 조회 or 생성
    Long roomId = chatService.getOrCreateChatRoom(user.getId(), otherUserId);

    redirectAttributes.addFlashAttribute("roomId", roomId);

    return "redirect:/chat";
  }

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
      model.addAttribute("selectedRoom", null); // 빈 화면
    }

    return "chat";
  }

  @GetMapping("/api/chat/{roomId}")
  public String getChatRoom(@PathVariable Long roomId, Model model,
                            Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    model.addAttribute("loginUser", user);

    ChatRoomResponse chatRoom = chatService.findById(roomId);
    System.out.println(chatRoom.getUser1().getNickname());
    System.out.println(chatRoom.getUser2().getNickname());
    model.addAttribute("selectedRoom", chatRoom);
    return "chat :: chatFragment";
  }

  // 채팅 추가
  @PostMapping("/api/chats")
  public ResponseEntity<Chat> addChat(@RequestBody ChatRequest request) {
    Chat savedChat = chatService.saveChat(request);
    return ResponseEntity.ok(savedChat);
  }

  // 롱폴링
  @GetMapping("/api/chat/updates/{roomId}")
  @ResponseBody
  public ResponseEntity<List<Chat>> getNewChats(
          @PathVariable Long roomId,
          @RequestParam(required = false) Long lastChatId) throws InterruptedException {
    long startTime = System.currentTimeMillis();
    long maxWait = 30_000; // 최대 30초 대기
    List<Chat> newChats;

    while (System.currentTimeMillis() - startTime < maxWait) {
      newChats = chatService.getChatsAfterId(roomId, lastChatId);
      if (!newChats.isEmpty()) {
        return ResponseEntity.ok(newChats);
      }
      Thread.sleep(1000); // 1초 대기 후 재조회
    }

    return ResponseEntity.ok(List.of());
  }

}
