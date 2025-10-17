package EST.Baemin.Manager.Exception;

public class ChatRoomNotFoundException extends RuntimeException {

  public ChatRoomNotFoundException(Long id) {
    super("채팅방이 존재하지 않습니다. id=" + id);
  }
}