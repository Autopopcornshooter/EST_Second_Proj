-- CHAT_ROOM 테이블용 데이터
INSERT INTO CHAT_ROOM (USER1ID, USER2ID, UPDATED_AT)
VALUES (101, 102, CURRENT_TIMESTAMP);

-- CHAT 테이블용 데이터
INSERT INTO CHAT (CHAT_ROOM_ID, SENDER_ID, MESSAGE, CREATED_AT)
VALUES
    (1, 101, '안녕하세요!', CURRENT_TIMESTAMP),
    (1, 102, '안녕하세요! 주문 관련 문의 드립니다.', CURRENT_TIMESTAMP),
    (1, 101, '네, 어떤 부분이 궁금하신가요?', CURRENT_TIMESTAMP),
    (1, 102, '상품 배송 상태를 알고 싶어요.', CURRENT_TIMESTAMP),
    (1, 101, '확인해보니 오늘 오후 도착 예정입니다.', CURRENT_TIMESTAMP),
    (1, 102, '좋아요, 감사합니다!', CURRENT_TIMESTAMP);