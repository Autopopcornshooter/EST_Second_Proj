package EST.Baemin.Manager.repository;

import EST.Baemin.Manager.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
  List<ChatRoom> findByUser1IdOrUser2Id(Long userId, Long userId1);

  List<ChatRoom> id(long id);
}
