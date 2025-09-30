// 식당 등록 로직
document.getElementById("restaurantForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // 기본 form 제출 막기

    // 입력값 모으기
    const data = {
    mainMenu: document.getElementById("menuName").value,
    price: document.getElementById("menuPrice").value,
    description: document.getElementById("menuDesc").value,
    address: document.getElementById("location").value
};

    try {
    const response = await fetch("/api/restaurants", {
    method: "POST",
    headers: {
    "Content-Type": "application/json"
},
    body: JSON.stringify(data)
});

    if (response.ok) {
    alert("등록을 성공하였습니다!");
    window.location.href = "/api/restaurants"; // 등록 후 목록 페이지로 이동
} else {
    alert("등록을 실패 하였습니다. : " + response.status);
}
} catch (error) {
    console.error("에러 발생:", error);
    alert("서버 오류 발생으로 인하여 등록할 수 없습니다.");
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

// 등록/수정 구분 로직
// document.getElementById("restaurantForm").addEventListener("submit", async function(event) {
//     event.preventDefault();
//
//     const id = this.getAttribute("data-id"); // 수정할 때는 id 있음
//     const data = {
//         mainMenu: document.getElementById("menuName").value,
//         price: document.getElementById("menuPrice").value,
//         description: document.getElementById("menuDesc").value,
//         address: document.getElementById("location").value
//     };
//
//     const url = id ? `/api/restaurants/${id}` : "/api/restaurants";
//     const method = id ? "PUT" : "POST";
//
//     try {
//         const response = await fetch(url, {
//             method: method,
//             headers: { "Content-Type": "application/json" },
//             body: JSON.stringify(data)
//         });
//
//         if (response.ok) {
//             alert(id ? "수정 성공!" : "등록 성공!");
//             window.location.href = "/api/restaurants";
//         } else {
//             alert("실패: " + response.status);
//         }
//     } catch (error) {
//         console.error("에러 발생:", error);
//         alert("서버 오류 발생");
//     }
// });
