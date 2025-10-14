package EST.Baemin.Manager.repository;

import EST.Baemin.Manager.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
  List<ChatRoom> findByUser1IdOrUser2IdOrderByUpdatedAtDesc(Long userId, Long userId1);

  Optional<ChatRoom> findByUser1IdAndUser2Id(Long userId, Long otherUserId);
}
