package EST.Baemin.Manager.service;

import EST.Baemin.Manager.Exception.ChatRoomNotFoundException;
import EST.Baemin.Manager.domain.Chat;
import EST.Baemin.Manager.domain.ChatRoom;
import EST.Baemin.Manager.dto.ChatRequest;
import EST.Baemin.Manager.dto.ChatRoomResponse;
import EST.Baemin.Manager.repository.ChatRepository;
import EST.Baemin.Manager.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
  private final ChatRepository chatRepository;
  private final ChatRoomRepository chatRoomRepository;

  public ChatService(ChatRepository chatRepository, ChatRoomRepository chatRoomRepository) {
    this.chatRepository = chatRepository;
    this.chatRoomRepository = chatRoomRepository;
  }

  // Create Chat
  public Chat savaChat(ChatRequest request) {
    return chatRepository.save(request.toEntity());
  }

  public List<ChatRoom> getChatRoomsByUserId(Long userId) {
    return chatRoomRepository.findByUser1IdOrUser2Id(userId, userId);
  }

  public ChatRoomResponse getChatRoomById(long id) {
    ChatRoom chatRoom = chatRoomRepository.findById(id)
                                .orElseThrow(() -> new ChatRoomNotFoundException(id));

    return new ChatRoomResponse(chatRoom);
  }

}
