document.addEventListener("DOMContentLoaded", function () {
    // CSRF 토큰
    const csrfTokenMeta = document.querySelector('meta[name="_csrf"]');
    const csrfHeaderMeta = document.querySelector('meta[name="_csrf_header"]');

    if (!csrfTokenMeta || !csrfHeaderMeta) {
        console.error("CSRF 메타태그를 찾을 수 없습니다!");
        return;
    }

    const csrfToken = csrfTokenMeta.content;
    const csrfHeader = csrfHeaderMeta.content;

    // 식당 등록/수정
    const form = document.getElementById("restaurantForm");
    if (form) {
        form.addEventListener("submit", async function(event) {
            event.preventDefault();

            const id = this.getAttribute("data-id");

            const data = {
                mainMenu: document.getElementById("menuName").value,
                price: document.getElementById("menuPrice").value,
                description: document.getElementById("menuDesc").value,
                address: document.getElementById("location").value
            };

            const url = id ? `/api/restaurants/${id}` : "/api/restaurants";
            const method = id ? "PUT" : "POST";

            try {
                const response = await fetch(url, {
                    method: method,
                    headers: {
                        "Content-Type": "application/json",
                        [csrfHeader]: csrfToken
                    },
                    credentials: 'include',
                    body: JSON.stringify(data)
                });

                if (response.ok) {
                    alert(id ? "수정 성공!" : "등록 성공!");
                    window.location.href = "/api/restaurants";
                } else {
                    alert("실패: " + response.status);
                }
            } catch (error) {
                console.error("에러 발생:", error);
                alert("서버 오류 발생으로 인해 저장할 수 없습니다.");
            }
        });
    }

    // 식당 삭제 (전역 함수로 설정)
    window.deleteRestaurant = function(id) {
        if (!confirm("정말 삭제하시겠습니까?")) return;

        fetch(`/api/restaurants/${id}`, {
            method: "DELETE",
            headers: { [csrfHeader]: csrfToken },
            credentials: 'include'
        })
            .then(response => {
                if (response.ok) {
                    alert("삭제 성공!");
                    window.location.href = "/api/restaurants";
                } else {
                    alert("삭제 실패: " + response.status);
                }
            })
            .catch(error => {
                console.error("삭제 오류:", error);
                alert("서버 오류 발생");
            });
    };

    // 좋아요
    const likeBtn = document.querySelector(".like-btn");
    const likeCountSpan = document.getElementById("like-count");

    if (likeBtn) {
        likeBtn.addEventListener("click", async () => {
            const restaurantId = likeBtn.getAttribute("data-id");

            const response = await fetch(`/api/restaurants/${restaurantId}/like`, {
                method: "POST",
                credentials: 'include'
            });

            if (response.ok) {
                const result = await response.json();
                likeCountSpan.textContent = `좋아요 ${result.likeCount} 👍`;
                likeBtn.textContent = result.liked ? "좋아요 취소" : "좋아요";
            } else {
                alert("좋아요 처리 오류");
            }
        });
    }
});






// // 식당 등록/수정 로직
// document.getElementById("restaurantForm").addEventListener("submit", async function(event) {
//     event.preventDefault(); // 기본 form 제출 막기
//
//     // form에 data-id 속성 추가: 수정 시 id 있음
//     const id = this.getAttribute("data-id");
//
//     // CSRF 토큰 가져오기
//     const csrfToken = document.querySelector('meta[name="_csrf"]').content;
//     const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
//
//     // 입력값 모으기
//     const data = {
//     mainMenu: document.getElementById("menuName").value,
//     price: document.getElementById("menuPrice").value,
//     description: document.getElementById("menuDesc").value,
//     address: document.getElementById("location").value
// };
//
//     // 등록/수정 구분
//     const url = id ? `/api/restaurants/${id}` : "/api/restaurants"; // id 있으면 수정, 없으면 등록
//     const method = id ? "PUT" : "POST";
//
//     try {
//         const response = await fetch(url, {
//             method: method,
//             headers: {
//                 "Content-Type": "application/json",
//                 // 추가 줄
//                 [csrfHeader]: csrfToken,
//             },
//             // 세션 큐키 유지
//             credentials: 'include',
//             body: JSON.stringify(data)
//         });
//
//         if (response.ok) {
//             alert(id ? "수정 성공!" : "등록 성공!");
//             window.location.href = "/api/restaurants"; // 완료 후 목록 페이지로 이동
//         } else {
//             alert("실패: " + response.status);
//         }
//     } catch (error) {
//         console.error("에러 발생:", error);
//         alert("서버 오류 발생으로 인해 저장할 수 없습니다.");
//     }
// });
//
// // 식당 삭제하기 로직
// function deleteRestaurant(id) {
//     if (!confirm("정말 삭제하시겠습니까?")) return;
//
//     // CSRF 토큰과 헤더 가져오기
//     const csrfToken = document.querySelector('meta[name="_csrf"]').content;
//     const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
//
//     fetch(`/api/restaurants/${id}`, {
//         method: "DELETE",
//         headers: {
//             [csrfHeader]: csrfToken
//         },
//         credentials: 'include', // 세션 쿠키 전달
//     })
//         .then(response => {
//             if (response.ok) {
//                 alert("삭제를 성공하였습니다!");
//                 window.location.href = "/api/restaurants"; // 삭제 후 목록 페이지로 이동
//             } else {
//                 alert("삭제를 실패하였습니다. : " + response.status);
//             }
//         })
//         .catch(error => {
//             console.error("삭제 중 오류가 발생 하였습니다. :", error);
//             alert("서버 오류가 발생으로 인하여 등록할 수 없습니다.");
//         });
// }
//
// // 좋아요 로직
// document.addEventListener("DOMContentLoaded", function () {
//     const likeBtn = document.querySelector(".like-btn");
//     const likeCountSpan = document.getElementById("like-count");
//
//     if (likeBtn) {
//         likeBtn.addEventListener("click", async () => {
//             const restaurantId = likeBtn.getAttribute("data-id");
//
//             const response = await fetch(`/api/restaurants/${restaurantId}/like`, {
//                 method: "POST",
//             });
//
//             if (response.ok) {
//                 const result = await response.json();
//                 likeCountSpan.textContent = `좋아요 ${result.likeCount} 👍`;
//                 likeBtn.textContent = result.liked ? "좋아요 취소" : "좋아요";
//             } else {
//                 alert("좋아요 처리 중 오류가 발생하였습니다.");
//             }
//
//         });
//     }
// });

$(document).ready(function() {
    $(".chat-btn").click(function() {
        const otherUserId = $(this).data("userid"); // th:data-userId로 넣은 값 가져오기
        window.location.href = `/api/chat/start/${otherUserId}`;
    });
});