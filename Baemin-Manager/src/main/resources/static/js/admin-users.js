function submitSearch() {
  const keyword = document.getElementById('keywordInput').value.trim();
  if (!keyword) return;
  window.location.href = `/admin/users/search/${encodeURIComponent(keyword)}/1`;
}


$(document).ready(function() {
  const token = $("meta[name='_csrf']").attr("content");
  const header = $("meta[name='_csrf_header']").attr("content");

  $(document).on("click", ".status-cell", function() {
    const $cell = $(this);
    const userId = $cell.data("id");
    const currentStatus = $cell.text().trim();

    const confirmMessage = currentStatus === "회원"
        ? "이 회원을 탈퇴시키겠습니까?"
        : "이 사용자를 다시 활성화(회원 상태)시키겠습니까?";

    if (!confirm(confirmMessage)) return;

    $.ajax({
      url: `/admin/users/status/${userId}`,
      type: "PATCH",
      beforeSend: function(xhr) {
        xhr.setRequestHeader(header, token); // ✅ CSRF 토큰 추가
      },
      success: function(updatedUser) {
        const newStatusText = updatedUser.active ? "회원" : "탈퇴";
        $cell.text(newStatusText);
        alert(`상태가 '${newStatusText}'(으)로 변경되었습니다.`);
      },
      error: function(xhr) {
        alert("상태 변경에 실패했습니다: " + xhr.responseText);
      }
    });
  });
});