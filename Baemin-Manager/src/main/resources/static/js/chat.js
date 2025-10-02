// // 롱 폴링을 위한 함수
// let lastChatId = null;
//
// function fetchNewChats(roomId) {
//   $.ajax({
//     url: "/api/chat/updates/" + roomId + (lastChatId ? "?lastChatId=" + lastChatId : ""),
//     type: "GET",
//     success: function(chats) {
//       if (chats.length > 0) {
//         // 마지막 채팅 ID 업데이트
//         lastChatId = chats[chats.length - 1].id;
//
//         // 새 채팅이 생기면 채팅방 전체 새로 로드
//         $.ajax({
//           url: "/api/chat/" + roomId,
//           type: "GET",
//           success: function (html) {
//             $("#chat-area").replaceWith(html);
//             $("#chatting-content").scrollTop($("#chatting-content")[0].scrollHeight);
//           }
//         });
//       }
//       // 항상 다시 롱폴링
//       fetchNewChats(roomId);
//     },
//     error: function() {
//       // 오류 시 3초 후 재시도
//       setTimeout(() => fetchNewChats(roomId), 3000);
//     }
//   });
// }
//
// $(document).ready(function() {
//   $(".chatting-list-item").on("click", function () {
//     let roomId = $(this).find(".room-id").val();
//
//     $.ajax({
//       url: "/api/chat/" + roomId,
//       type: "GET",
//       success: function (html) {
//         $("#chat-area").replaceWith(html);
//
//         lastChatId = null; // 초기화
//         fetchNewChats(roomId); // 롱폴링 시작
//       }
//     });
//   });
// });
//
// $(document).on("click", "#send-btn", function() {
//   let message = $("#message-input").val().trim();
//   let roomId = $("#message-input").data("room-id");
//   let senderId = $("#message-input").data("sender-id");
//
//   if (!message) {
//     alert("메시지를 입력하세요.");
//     return;
//   }
//
//   let requestData = {
//     chatRoomId: roomId,
//     senderId: senderId,
//     message: message
//   };
//
//   $.ajax({
//     url: "/api/chats",
//     type: "POST",
//     contentType: "application/json",
//     data: JSON.stringify(requestData),
//     success: function(savedChat) {
//       console.log("채팅 전송에 성공했습니다.")
//     },
//     error: function(xhr) {
//       console.error("채팅 전송 실패:", xhr);
//       alert("채팅 전송에 실패했습니다.");
//     }
//   });
// });

$(document).ready(function() {
  var socket = new SockJS('/ws');
  var stompClient = Stomp.over(socket);
  var currentRoomId = $("#message-input").data("room-id") || null;
  var loginUserId = $("#message-input").data("sender-id");

  stompClient.connect({}, function(frame) {
    console.log("WebSocket connected:", frame);

    // 페이지에 표시된 모든 채팅방 구독
    $(".chatting-list-item").each(function() {
      var roomId = $(this).find(".room-id").val();

      stompClient.subscribe('/topic/' + roomId, function(message) {
        var chat = JSON.parse(message.body);

        // 사이드바 마지막 대화 내용 갱신
        $(".chatting-list-item").each(function() {
          if ($(this).find(".room-id").val() == roomId) {
            $(this).find(".last-comment").text(chat.message);
          }
        });

        // 현재 열려 있는 방이면 채팅창에도 append
        if (currentRoomId == roomId) {
          var isMine = chat.senderId == loginUserId;
          var containerClass = "d-flex mb-2 " + (isMine ? "justify-content-end" : "justify-content-start");
          var messageClass = isMine ? "chatting-item2" : "chatting-item1";

          var now = new Date();
          var hours = now.getHours();
          var minutes = now.getMinutes().toString().padStart(2, "0");
          var ampm = hours >= 12 ? "오후" : "오전";
          hours = hours % 12 || 12;

          var htmlMessage = chat.message.replace(/\n/g, "<br>");
          var chatHtml = `
            <div class="${containerClass}">
              <div class="${messageClass}">${htmlMessage}</div>
              <p class="time ms-1 mt-auto mb-2">${ampm} ${hours}:${minutes}</p>
            </div>
          `;
          $("#chatting-content").append(chatHtml);
          $("#chatting-content").scrollTop($("#chatting-content")[0].scrollHeight);
        }
      });
    });

    // 채팅방 클릭 시
    $(document).on("click", ".chatting-list-item", function () {
      var roomId = $(this).find(".room-id").val();
      currentRoomId = roomId;

      $.ajax({
        url: "/api/chat/" + roomId,
        type: "GET",
        success: function(html) {
          $("#chat-area").replaceWith(html);
        }
      });
    });

    // 메시지 전송
    $(document).on("click", "#send-btn", function() {
      var message = $("#message-input").val().trim();
      if (!message || !currentRoomId) {
        alert("메시지를 입력하고 채팅방을 선택하세요.");
        return;
      }

      var chatRequest = {
        chatRoomId: currentRoomId,
        senderId: loginUserId,
        message: message
      };

      stompClient.send("/app/chat/" + currentRoomId, {}, JSON.stringify(chatRequest));
      $("#message-input").val("");
    });
  });
});