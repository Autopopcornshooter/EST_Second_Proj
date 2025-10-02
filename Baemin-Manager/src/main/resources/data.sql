
INSERT INTO USERS (CREATED_AT,UPDATED_AT,LOGIN_ID, NICKNAME, PASSWORD, STORE_NAME)
VALUES
--     aabbccDD12#
(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'firetruck433','이경원','$2a$10$eftwQu.3tfqZ43Eoc0wZc.t5wB31SSaoBGAH9m7Nk13wI73zp8eRG','닭꼬치'),
--     abcD1@3
(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'abcd111','김철수','$2a$10$joAuRtdFdo1dkuMqIlV8zOMCO7Y.aJd02jvgKwbSADoLFAqHmR4eS','짜장면'),
(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'firetruck433@gmail.com','이경원 (자동팝콘발사기)',null,null);

-- CHAT_ROOM 테이블용 데이터
INSERT INTO CHAT_ROOM (USER1_ID, USER2_ID, UPDATED_AT)
VALUES (1, 2, CURRENT_TIMESTAMP);

-- CHAT 테이블용 데이터
INSERT INTO CHAT (CHAT_ROOM_ID, SENDER_ID, MESSAGE, CREATED_AT)
VALUES
    (1, 1, '안녕하세요!', CURRENT_TIMESTAMP),
    (1, 2, '안녕하세요! 주문 관련 문의 드립니다.', CURRENT_TIMESTAMP),
    (1, 1, '네, 어떤 부분이 궁금하신가요?', CURRENT_TIMESTAMP),
    (1, 2, '상품 배송 상태를 알고 싶어요.', CURRENT_TIMESTAMP),
    (1, 1, '확인해보니 오늘 오후 도착 예정입니다.', CURRENT_TIMESTAMP),
    (1, 2, '좋아요, 감사합니다!', CURRENT_TIMESTAMP);

