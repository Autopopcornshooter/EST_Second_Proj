package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.Chat;
import EST.Baemin.Manager.domain.ChatRoom;
import EST.Baemin.Manager.dto.ChatRequest;
import EST.Baemin.Manager.dto.ChatRoomResponse;
import EST.Baemin.Manager.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {
  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }


  @GetMapping("/chat")
  public String chatPage(Model model) {
    Long loggedInUserId = 101L; // 현재 로그인 유저
    String myNickname = "홍길동";

    // DB에서 채팅방 조회 (채팅방 하나라고 가정)
    List<ChatRoom> chatRooms = chatService.getChatRoomsByUserId(loggedInUserId);

    // 임시 상대방 닉네임
    Map<Long, String> roomOtherNicknames = new HashMap<>();
    for (ChatRoom room : chatRooms) {
      String otherNickname = room.getUser1Id().equals(loggedInUserId)
                                     ? "상대방닉네임1"
                                     : "상대방닉네임2";
      roomOtherNicknames.put(room.getId(), otherNickname);
    }

    model.addAttribute("myId", loggedInUserId);
    model.addAttribute("myNickname", myNickname);
    model.addAttribute("chatRooms", chatRooms);
    model.addAttribute("roomOtherNicknames", roomOtherNicknames);

    // 선택된 채팅방을 chatRooms[0]으로
    if (!chatRooms.isEmpty()) {
      model.addAttribute("selectedRoom", chatRooms.get(0));
    }

    return "chat";
  }

  @GetMapping("/api/chat/{roomId}")
  public String getChatRoom(@PathVariable Long roomId, Model model) {
    ChatRoomResponse chatroom = chatService.findById(roomId);
    model.addAttribute("chatRoom", chatroom);
    return "chat :: chatFragment";
  }

  // 2) 채팅 추가
  @PostMapping("/api/chats")
  public ResponseEntity<Chat> addChat(@RequestBody ChatRequest request) {
    Chat savedChat = chatService.saveChat(request);
    return ResponseEntity.ok(savedChat);
  }

}
