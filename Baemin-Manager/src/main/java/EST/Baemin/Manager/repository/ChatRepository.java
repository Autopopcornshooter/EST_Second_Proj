package EST.Baemin.Manager.repository;

import EST.Baemin.Manager.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
  // 채팅방의 모든 채팅 조회 (초기 로딩용)
  List<Chat> findByChatRoomIdOrderByCreatedAtAsc(Long chatRoomId);

  // lastChatId 이후 채팅만 조회 (롱폴링용)
  List<Chat> findByChatRoomIdAndIdGreaterThanOrderByCreatedAtAsc(Long chatRoomId, Long lastChatId);

}
