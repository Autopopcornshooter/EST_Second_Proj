// 식당 등록/수정 로직
document.getElementById("restaurantForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // 기본 form 제출 막기

    // form에 data-id 속성 추가: 수정 시 id 있음
    const id = this.getAttribute("data-id");

    // 입력값 모으기
    const data = {
    mainMenu: document.getElementById("menuName").value,
    price: document.getElementById("menuPrice").value,
    description: document.getElementById("menuDesc").value,
    address: document.getElementById("location").value
};

    // 등록/수정 구분
    const url = id ? `/api/restaurants/${id}` : "/api/restaurants"; // id 있으면 수정, 없으면 등록
    const method = id ? "PUT" : "POST";

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            alert(id ? "수정 성공!" : "등록 성공!");
            window.location.href = "/api/restaurants"; // 완료 후 목록 페이지로 이동
        } else {
            alert("실패: " + response.status);
        }
    } catch (error) {
        console.error("에러 발생:", error);
        alert("서버 오류 발생으로 인해 저장할 수 없습니다.");
    }
});

// 식당 삭제하기 로직
function deleteRestaurant(id) {
    if (!confirm("정말 삭제하시겠습니까?")) return;

    fetch(`/api/restaurants/${id}`, {
        method: "DELETE"
    })
        .then(response => {
            if (response.ok) {
                alert("삭제를 성공하였습니다!");
                window.location.href = "/api/restaurants"; // 삭제 후 목록 페이지로 이동
            } else {
                alert("삭제를 실패하였습니다. : " + response.status);
            }
        })
        .catch(error => {
            console.error("삭제 중 오류가 발생 하였습니다. :", error);
            alert("서버 오류가 발생으로 인하여 등록할 수 없습니다.");
        });
}

// 좋아요 로직
document.addEventListener("DOMContentLoaded", function () {
    const likeBtn = document.querySelector(".like-btn");
    const likeCountSpan = document.getElementById("like-count");

    if (likeBtn) {
        likeBtn.addEventListener("click", async () => {
            const restaurantId = likeBtn.getAttribute("data-id");

            const response = await fetch(`/api/restaurants/${restaurantId}/like`, {
                method: "POST",
            });

            if (response.ok) {
                const result = await response.json();
                likeCountSpan.textContent = `좋아요 ${result.likeCount} 👍`;
                likeBtn.textContent = result.liked ? "좋아요 취소" : "좋아요";
            } else {
                alert("좋아요 처리 중 오류가 발생하였습니다.");
            }

        });
    }
});