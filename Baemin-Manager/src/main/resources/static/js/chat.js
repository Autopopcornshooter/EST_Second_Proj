$(".chatting-list-item").on("click", function () {
  let roomId = $(this).find(".room-id").val();

  $.ajax({
    url: "/api/chat/" + roomId,   // 컨트롤러 매핑 주소
    type: "GET",                   // 필요에 따라 POST 가능
    success: function (html) {
      console.log("성공:", response);
      $("#chat-area").html(html);
    },
    error: function (xhr) {
      console.log("에러: 채팅방을 불러오기 실패", xhr)
    }
  });
});


$("#send-btn").on("click", function() {
  let message = $("#message-input").val().trim();
  let roomId = $("#message-input").data("room-id");
  let senderId = $("#message-input").data("sender-id");

  if (!message) {
    alert("메시지를 입력하세요.");
    return;
  }

  let requestData = {
    roomId: roomId,
    message: message
  };

  $.ajax({
    url: "/api/chats",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(requestData),
    success: function(savedChat) {
      // 성공 시 메시지 입력창 비우기
      $("#message-input").val("");

      // 새로 추가된 채팅 DOM 생성
      let newChatHtml = `
                    <div class="d-flex ${savedChat.senderId == 101 ? 'justify-content-end' : ''}">
                        <div class="${savedChat.senderId == 101 ? 'chatting-item2' : 'chatting-item1'}">
                            ${savedChat.message}
                        </div>
                        <p class="time ms-1 mt-auto mb-2">
                            ${new Date(savedChat.createdAt).toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'})}
                        </p>
                    </div>
                `;
      $("#chatting-content").append(newChatHtml);
      $("#chatting-content").scrollTop($("#chatting-content")[0].scrollHeight);
    },
    error: function(xhr) {
      console.error("채팅 전송 실패:", xhr);
      alert("채팅 전송에 실패했습니다.");
    }
  });
});