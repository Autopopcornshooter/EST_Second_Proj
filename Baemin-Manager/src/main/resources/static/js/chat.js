// 롱 폴링을 위한 함수
let lastChatId = null;

function fetchNewChats(roomId) {
  $.ajax({
    url: "/api/chat/updates/" + roomId + (lastChatId ? "?lastChatId=" + lastChatId : ""),
    type: "GET",
    success: function(chats) {
      if (chats.length > 0) {
        // 마지막 채팅 ID 업데이트
        lastChatId = chats[chats.length - 1].id;

        // 새 채팅이 생기면 채팅방 전체 새로 로드
        $.ajax({
          url: "/api/chat/" + roomId,
          type: "GET",
          success: function (html) {
            $("#chat-area").replaceWith(html);
            $("#chatting-content").scrollTop($("#chatting-content")[0].scrollHeight);
          }
        });
      }
      // 항상 다시 롱폴링
      fetchNewChats(roomId);
    },
    error: function() {
      // 오류 시 3초 후 재시도
      setTimeout(() => fetchNewChats(roomId), 3000);
    }
  });
}

$(document).ready(function() {
  $(".chatting-list-item").on("click", function () {
    let roomId = $(this).find(".room-id").val();

    $.ajax({
      url: "/api/chat/" + roomId,
      type: "GET",
      success: function (html) {
        $("#chat-area").replaceWith(html);

        lastChatId = null; // 초기화
        fetchNewChats(roomId); // 롱폴링 시작
      }
    });
  });
});

$(document).on("click", "#send-btn", function() {
  let message = $("#message-input").val().trim();
  let roomId = $("#message-input").data("room-id");
  let senderId = $("#message-input").data("sender-id");

  if (!message) {
    alert("메시지를 입력하세요.");
    return;
  }

  let requestData = {
    chatRoomId: roomId,
    senderId: senderId,
    message: message
  };

  $.ajax({
    url: "/api/chats",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(requestData),
    success: function(savedChat) {
      console.log("채팅 전송에 성공했습니다.")
    },
    error: function(xhr) {
      console.error("채팅 전송 실패:", xhr);
      alert("채팅 전송에 실패했습니다.");
    }
  });
});

