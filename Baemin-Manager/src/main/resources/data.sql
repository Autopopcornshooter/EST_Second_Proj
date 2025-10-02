
INSERT INTO USERS (CREATED_AT,UPDATED_AT,LOGIN_ID, NICKNAME, PASSWORD, STORE_NAME)
VALUES
--     aabbccDD12#
(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'firetruck433','이경원','$2a$10$eftwQu.3tfqZ43Eoc0wZc.t5wB31SSaoBGAH9m7Nk13wI73zp8eRG','닭꼬치'),
--     abcD1@3
(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'abcd111','김철수','$2a$10$joAuRtdFdo1dkuMqIlV8zOMCO7Y.aJd02jvgKwbSADoLFAqHmR4eS','짜장면'),
(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'firetruck433@gmail.com','이경원 (자동팝콘발사기)',null,null);

-- CHAT_ROOM 테이블용 데이터
INSERT INTO CHAT_ROOM (USER1_ID, USER2_ID, UPDATED_AT)
VALUES (1, 2, CURRENT_TIMESTAMP),
       (1, 3, CURRENT_TIMESTAMP);

-- Chats
INSERT INTO chat (chat_room_id, sender_id, message, created_at)
VALUES
    (1, 1, '오늘 점심은 뭐 드셨나요?', CURRENT_TIMESTAMP),
    (1, 2, '저는 김치찌개 먹었어요.', CURRENT_TIMESTAMP),
    (1, 1, '맛있겠네요!', CURRENT_TIMESTAMP),
    (1, 2, '네, 홍길동님은요?', CURRENT_TIMESTAMP),
    (1, 1, '저는 비빔밥 먹었어요.', CURRENT_TIMESTAMP);

INSERT INTO chat (chat_room_id, sender_id, message, created_at)
VALUES
    (2, 1, '영희님, 카페 준비 완료하셨나요?', CURRENT_TIMESTAMP),
    (2, 3, '네, 다 준비됐어요.', CURRENT_TIMESTAMP),
    (2, 1, '좋아요. 곧 도착할게요.', CURRENT_TIMESTAMP),
    (2, 3, '알겠습니다.', CURRENT_TIMESTAMP),
    (2, 1, '감사합니다!', CURRENT_TIMESTAMP);
