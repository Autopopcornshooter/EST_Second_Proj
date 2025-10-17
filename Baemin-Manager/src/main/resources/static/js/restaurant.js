console.log("restaurant.js 로드됨");

document.addEventListener("DOMContentLoaded", () => {
    // CSRF 토큰 가져오기
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
            const fileInput = document.getElementById("imageInput");
            let imageUrl = null;
                //이미지 업로드
            if (fileInput.files.length > 0) {
                const formData = new FormData();
                formData.append("file", fileInput.files[0]);

                const uploadRes = await fetch("/api/restaurants/upload", {
                    method: "POST",
                    body: formData,
                    credentials: "include",
                    headers: {
                        [csrfHeader]: csrfToken
                    }
                });

                if (!uploadRes.ok) {
                    alert("이미지 업로드 실패");
                    return;
                }
                imageUrl = await uploadRes.text(); // ← S3 URL
            }
            const data = {
                mainMenu: document.getElementById("menuName").value,
                price: document.getElementById("menuPrice").value,
                description: document.getElementById("menuDesc").value,
                address: document.getElementById("location").value,
                imageUrl: imageUrl
            };

            const url = id ? `/api/restaurants/${id}` : "/api/restaurants";
            const method = id ? "PUT" : "POST";

            try {
                const response = await fetch(url, {
                    method,
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
    // const form = document.getElementById("restaurantForm");
    // if (form) {
    //     form.addEventListener("submit", async function(event) {
    //         event.preventDefault();
    //         const id = this.getAttribute("data-id");
    //
    //         const data = {
    //             mainMenu: document.getElementById("menuName").value,
    //             price: document.getElementById("menuPrice").value,
    //             description: document.getElementById("menuDesc").value,
    //             address: document.getElementById("location").value
    //         };
    //
    //         const url = id ? `/api/restaurants/${id}` : "/api/restaurants";
    //         const method = id ? "PUT" : "POST";
    //
    //         try {
    //             const response = await fetch(url, {
    //                 method,
    //                 headers: {
    //                     "Content-Type": "application/json",
    //                     [csrfHeader]: csrfToken
    //                 },
    //                 credentials: 'include',
    //                 body: JSON.stringify(data)
    //             });
    //
    //             if (response.ok) {
    //                 alert(id ? "수정 성공!" : "등록 성공!");
    //                 window.location.href = "/api/restaurants";
    //             } else {
    //                 alert("실패: " + response.status);
    //             }
    //         } catch (error) {
    //             console.error("에러 발생:", error);
    //             alert("서버 오류 발생으로 인해 저장할 수 없습니다.");
    //         }
    //     });
    // }

    // 식당 삭제
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
                } else if(response.status === 403) {
                    alert("삭제 실패: 권한이 없습니다.");
                } else {
                    alert("삭제 실패: " + response.status);
                }
            })
            .catch(error => {
                console.error("삭제 오류:", error);
                alert("서버 오류 발생");
            });
    };

    // 좋아요 버튼 통합 처리
    const likeBtns = document.querySelectorAll(".like-btn");
    if (!likeBtns.length) {
        console.warn("좋아요 버튼 없음");
        return;
    }

    likeBtns.forEach(btn => {
        console.log("좋아요 버튼 발견:", btn);

        btn.addEventListener("click", async () => {
            const restaurantId = btn.dataset.id;
            console.log("좋아요 클릭:", restaurantId);

            try {
                const response = await fetch(`/api/restaurants/${restaurantId}/like`, {
                    method: "POST",
                    headers: { [csrfHeader]: csrfToken },
                    credentials: 'include'
                });

                if (response.ok) {
                    const result = await response.json();

                    // 버튼 안의 텍스트와 카운트 갱신
                    const spanText = btn.querySelector("span:first-child");
                    const spanCount = btn.querySelector(".like-count");

                    if (spanText) spanText.textContent = result.liked ? "좋아요 취소" : "좋아요";
                    if (spanCount) spanCount.textContent = ` ${result.likeCount} 👍`;

                    // data-liked 갱신
                    btn.dataset.liked = result.liked;
                } else {
                    alert("좋아요 처리 실패");
                }
            } catch (error) {
                console.error("좋아요 오류:", error);
                alert("서버 오류 발생");
            }
        });
    });
});

$(document).ready(function() {
    $(".chat-btn").click(function() {
        const otherUserId = $(this).data("userid"); // th:data-userId로 넣은 값 가져오기
        window.location.href = `/api/chat/start/${otherUserId}`;
    });
});

const imageInput = document.getElementById("imageInput");
const imageBox = document.querySelector(".image-box img");

imageInput.addEventListener("change", () => {
    const file = imageInput.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
            imageBox.src = e.target.result; // 미리보기
        };
        reader.readAsDataURL(file);
    }
});
