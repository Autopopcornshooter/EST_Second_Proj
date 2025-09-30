package EST.Baemin.Manager.service;

import EST.Baemin.Manager.Exception.ChatRoomNotFoundException;
import EST.Baemin.Manager.domain.Chat;
import EST.Baemin.Manager.domain.ChatRoom;
import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.dto.ChatRequest;
import EST.Baemin.Manager.dto.ChatRoomResponse;
import EST.Baemin.Manager.repository.ChatRepository;
import EST.Baemin.Manager.repository.ChatRoomRepository;
import EST.Baemin.Manager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ChatService {
  private final ChatRepository chatRepository;
  private final ChatRoomRepository chatRoomRepository;
  private final UserRepository userRepository;

  public ChatService(ChatRepository chatRepository, ChatRoomRepository chatRoomRepository,  UserRepository userRepository) {
    this.chatRepository = chatRepository;
    this.chatRoomRepository = chatRoomRepository;
    this.userRepository = userRepository;
  }

  // Create Chat
  public Chat saveChat(ChatRequest request) {
    ChatRoom chatRoom = chatRoomRepository.findById(request.getChatRoomId())
                                .orElseThrow(() -> new ChatRoomNotFoundException(request.getChatRoomId()));

    Chat chat = request.toEntity(chatRoom);

    return chatRepository.save(chat);
  }

  public List<ChatRoomResponse> getChatRoomsByUserId(Long userId) {
    return chatRoomRepository.findByUser1IdOrUser2IdOrderByUpdatedAtDesc(userId, userId).stream()
                   .map(ChatRoomResponse::from)
                   .toList();
  }

  @Transactional
  public ChatRoomResponse findById(long id) {
    ChatRoom chatRoom = chatRoomRepository.findById(id)
                                .orElseThrow(() -> new ChatRoomNotFoundException(id));
    chatRoom.getChats();
    System.out.println("실행되나?");
    System.out.println(chatRoom.getUser1().getNickname());
    System.out.println(chatRoom.getUser1().getStoreName());
    System.out.println(chatRoom.getUser2().getNickname());
    System.out.println(chatRoom.getUser2().getStoreName());

    return ChatRoomResponse.from(chatRoom);
  }

  @Transactional
  public Long getOrCreateChatRoom(Long userId, Long otherUserId) {

    // 1. 기존 채팅방 조회 (user1 = userId, user2 = otherUserId)
    Optional<ChatRoom> existingRoom = chatRoomRepository
                                              .findByUser1IdAndUser2Id(userId, otherUserId);

    // 2. 없으면 반대로 조회 (user1 = otherUserId, user2 = userId)
    if (existingRoom.isEmpty()) {
      existingRoom = chatRoomRepository.findByUser1IdAndUser2Id(otherUserId, userId);
    }

    // 3. 있으면 반환
    if (existingRoom.isPresent()) {
      return existingRoom.get().getId();
    }

    // 4. 없으면 새 채팅방 생성
    User user1 = userRepository.findById(userId)
                         .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
    User user2 = userRepository.findById(otherUserId)
                         .orElseThrow(() -> new IllegalArgumentException("User not found: " + otherUserId));

    ChatRoom newRoom = ChatRoom.builder()
                               .user1(user1)
                               .user2(user2)
                               .build();

    chatRoomRepository.save(newRoom);

    return newRoom.getId();
  }

  // 마지막 채팅 ID 이후 새 채팅 조회 (롱폴링용)
  public List<Chat> getChatsAfterId(Long roomId, Long lastChatId) {
    if (lastChatId == null) {
      // 처음 조회하는 경우, 방의 모든 채팅 반환
      return chatRepository.findByChatRoomIdOrderByCreatedAtAsc(roomId);
    }
    // 마지막 ID 이후의 새 채팅만 반환
    return chatRepository.findByChatRoomIdAndIdGreaterThanOrderByCreatedAtAsc(roomId, lastChatId);
  }
}
