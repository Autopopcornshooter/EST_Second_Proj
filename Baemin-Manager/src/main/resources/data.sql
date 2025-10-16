/*(CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'firetruck433@gmail.com','이경원 (자동팝콘발사기)',null,null);

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
*/
-- Region
INSERT INTO region ( address, latitude, longitude, google_place_id)

VALUES ( '대한민국 서울특별시 강남구 테헤란로 1', 37.4979, 127.0276, 'place_001'),
       ( '대한민국 서울특별시 종로구 세종대로 175', 37.5735, 126.9780, 'place_002'),
       ( '대한민국 서울특별시 마포구 독막로 12', 37.5500, 126.9137, 'place_003'),
       ( '대한민국 서울특별시 송파구 백제고분로 123', 37.5145, 127.1052, 'place_004'),
       ( '대한민국 서울특별시 구로구 구로동 45', 37.4955, 126.8870, 'place_005'),
       ( '대한민국 서울특별시 강서구 화곡로 100', 37.5420, 126.8410, 'place_006'),
       ( '대한민국 서울특별시 서초구 서초대로 200', 37.4920, 127.0150, 'place_007'),
       ( '대한민국 서울특별시 용산구 이태원로 50', 37.5340, 126.9940, 'place_008'),
       ( '대한민국 서울특별시 동작구 상도로 120', 37.5050, 126.9490, 'place_009'),
       ( '대한민국 서울특별시 영등포구 여의대로 24', 37.5210, 126.9240, 'place_010'),
       ( '대한민국 서울특별시 은평구 불광로 87', 37.6190, 126.9230, 'place_011'),
       ( '대한민국 서울특별시 노원구 동일로 890', 37.6540, 127.0560, 'place_012'),
       ( '대한민국 인천광역시 서구 청마로 92', 37.586, 126.6742, 'place_013');


-- User
INSERT INTO users (nickname, login_id, password, store_name, profile_icon, created_at, updated_at, region_id,
                   role, is_active)
VALUES ('김철수', 'kimcs', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '철수분식', '/img/profile1.png',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 'ROLE_USER', true),
       ('이영희', 'leeyh', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '영희카페', '/img/profile2.png',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 'ROLE_USER', true),
       ('박민수', 'parkms', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '민수식당', '/img/profile3.png',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 'ROLE_USER', true),
       ('최지훈', 'choijh', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '지훈치킨', '/img/profile4.png',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 'ROLE_USER', true),
       ('정은지', 'jungej', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '은지쌀국수',
        '/img/profile5.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 'ROLE_USER', true),
       ('한수연', 'hansy', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '수연카페', '/img/profile6.png',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 'ROLE_USER', true),
       ('윤도현', 'yoondh', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '도현김밥', '/img/profile7.png',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 'ROLE_USER', true),
       ('조현우', 'chohwu', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '현우돈까스',
        '/img/profile8.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 'ROLE_USER', true),
       ('오세진', 'ohsj', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '세진커피', '/img/profile9.png',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 9, 'ROLE_USER', true),

       ('이서준', 'leesej', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '서준버거',
        '/img/profile10.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 10, 'ROLE_USER', true),
       ('배지민', 'baejm', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '지민족발',
        '/img/profile11.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 11, 'ROLE_USER', true);
--        ('홍다연', 'hongdy', '$2a$10$Am..pIExx64m8n7mgtiMb.BtPqq3UWZ3bQsgW2n5OgaUZzZUx7g4G', '다연샌드위치',
--         '/img/profile12.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 12, 'ROLE_USER', true);


INSERT INTO USERS (CREATED_AT, UPDATED_AT, LOGIN_ID, NICKNAME, PASSWORD, STORE_NAME, ROLE, IS_ACTIVE)
VALUES
--     abcD1@3
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'dlsdnd7928', '서울정인웅', '$2a$10$joAuRtdFdo1dkuMqIlV8zOMCO7Y.aJd02jvgKwbSADoLFAqHmR4eS',
 '서울짜장면', 'ROLE_USER', true),
--     abcD1@3

(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'dlsdnd123', '인천정인웅', '$2a$10$joAuRtdFdo1dkuMqIlV8zOMCO7Y.aJd02jvgKwbSADoLFAqHmR4eS',
 '인천짜장면', 'ROLE_USER', true),

(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ADMIN', '관리자', '$2a$10$joAuRtdFdo1dkuMqIlV8zOMCO7Y.aJd02jvgKwbSADoLFAqHmR4eS',
    '관리자용', 'ROLE_ADMIN', true);

-- Restrant
INSERT INTO restaurant ( name, main_menu, description, address, price, user_id, view, image_url, created_at,
                        updated_at, state)
VALUES ( '철수분식', '떡볶이', '매콤달콤한 즉석 떡볶이', '대한민국 서울특별시 강남구 테헤란로 1', 5000, 1, 0, '/img/res1.jpg', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,'공개'),
       ( '영희카페', '아메리카노', '신선한 원두로 내린 커피', '대한민국 서울특별시 종로구 세종대로 175', 4500, 2, 0, '/img/res2.jpg', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, '공개'),
       ( '민수식당', '김치찌개', '얼큰한 돼지고기 김치찌개', '대한민국 서울특별시 마포구 독막로 12', 7000, 3, 0, '/img/res3.jpg', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, '공개'),
       ( '지훈치킨', '양념치킨', '바삭하고 달콤한 치킨', '대한민국 서울특별시 송파구 백제고분로 123', 16000, 4, 0, '/img/res4.jpg', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, '공개'),
       ( '은지쌀국수', '소고기쌀국수', '진한 육수의 베트남식 쌀국수', '대한민국 서울특별시 구로구 구로동 45', 9000, 5, 0, '/img/res5.jpg', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,'공개'),
       ( '수연카페', '라떼', '부드러운 우유 라떼', '대한민국 서울특별시 강서구 화곡로 100', 4800, 6, 0, '/img/res6.jpg', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, '공개'),
       ( '도현김밥', '참치김밥', '든든한 참치마요 김밥', '대한민국 서울특별시 서초구 서초대로 200', 3500, 7, 0, '/img/res7.jpg', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, '공개'),
       ( '현우돈까스', '치즈돈까스', '고소한 치즈가 듬뿍', '대한민국 서울특별시 용산구 이태원로 50', 9500, 8, 0, '/img/res8.jpg', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, '공개'),
--        ( '세진커피', '카푸치노', '부드러운 거품의 커피', '서울특별시 동작구 상도로 120', 4800, 9, 0, '/img/res9.jpg', CURRENT_TIMESTAMP,
--         CURRENT_TIMESTAMP),
--        ( '서준버거', '불고기버거', '직접 구운 패티의 수제버거', '서울특별시 영등포구 여의대로 24', 8800, 10, 0, '/img/res10.jpg', CURRENT_TIMESTAMP,
--         CURRENT_TIMESTAMP),
--        ( '지민족발', '족발', '윤기가 흐르는 냄새 좋은 족발', '서울특별시 은평구 불광로 87', 25000, 11, 0, '/img/res11.jpg', CURRENT_TIMESTAMP,
--         CURRENT_TIMESTAMP),
       ( '인웅족발', '족발', '윤기가 흐르는 냄새 좋은 족발', '대한민국 서울특별시 노원구 ~~로 ~~~', 25000, 9, 0, '/img/res11.jpg', CURRENT_TIMESTAMP,
         CURRENT_TIMESTAMP, '공개'),
       ( '다연샌드위치', '클럽샌드위치', '든든한 점심 한 끼', '대한민국 인천광역시 서구 청마로 93', 6500, 10, 0, '/img/res12.jpg', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, '공개'),
       ( '아이스께끼', '아이스크림', '맛잇는 아이스크림', '대한민국 인천광역시 연수구 ~~로 ~~~', 25000, 11, 0, '/img/res11.jpg', CURRENT_TIMESTAMP,
         CURRENT_TIMESTAMP, '공개');

-- ChatRoom
INSERT INTO chat_room (user1_id, user2_id, updated_at)
VALUES
    (1, 2, CURRENT_TIMESTAMP), (1, 3, CURRENT_TIMESTAMP), (1, 4, CURRENT_TIMESTAMP),
    (2, 3, CURRENT_TIMESTAMP), (2, 5, CURRENT_TIMESTAMP), (2, 6, CURRENT_TIMESTAMP),
    (3, 4, CURRENT_TIMESTAMP), (3, 7, CURRENT_TIMESTAMP), (3, 8, CURRENT_TIMESTAMP),
    (4, 5, CURRENT_TIMESTAMP), (4, 9, CURRENT_TIMESTAMP), (4, 10, CURRENT_TIMESTAMP),
    (5, 6, CURRENT_TIMESTAMP), (5, 11, CURRENT_TIMESTAMP), (5, 12, CURRENT_TIMESTAMP),
    (6, 7, CURRENT_TIMESTAMP), (6, 8, CURRENT_TIMESTAMP), (6, 9, CURRENT_TIMESTAMP),
    (7, 8, CURRENT_TIMESTAMP), (7, 10, CURRENT_TIMESTAMP), (7, 11, CURRENT_TIMESTAMP),
    (8, 9, CURRENT_TIMESTAMP), (8, 12, CURRENT_TIMESTAMP), (9, 10, CURRENT_TIMESTAMP),
    (9, 11, CURRENT_TIMESTAMP), (10, 12, CURRENT_TIMESTAMP), (11, 12, CURRENT_TIMESTAMP);

-- Chat
INSERT INTO chat (chat_room_id, sender_id, message, created_at)
VALUES
-- 채팅방 1
(1, 1, '안녕하세요!', CURRENT_TIMESTAMP),
(1, 2, '안녕하세요, 반갑습니다.', CURRENT_TIMESTAMP),
(1, 1, '오늘 매출은 어떠세요?', CURRENT_TIMESTAMP),
(1, 2, '좋아요, 손님이 많네요.', CURRENT_TIMESTAMP),
(1, 1, '그렇군요!', CURRENT_TIMESTAMP),

-- 채팅방 2
(2, 1, '오늘 메뉴 추천해 주세요.', CURRENT_TIMESTAMP),
(2, 3, '저희 스페셜 메뉴는 이거에요.', CURRENT_TIMESTAMP),
(2, 1, '좋네요, 주문할게요.', CURRENT_TIMESTAMP),
(2, 3, '감사합니다!', CURRENT_TIMESTAMP),
(2, 1, '다음에도 부탁드려요.', CURRENT_TIMESTAMP),

-- 채팅방 3
(3, 1, '주말 준비는 잘 되고 있나요?', CURRENT_TIMESTAMP),
(3, 4, '네, 준비 완료했습니다.', CURRENT_TIMESTAMP),
(3, 1, '좋아요.', CURRENT_TIMESTAMP),
(3, 4, '감사합니다!', CURRENT_TIMESTAMP),
(3, 1, '다음 회의 때 봐요.', CURRENT_TIMESTAMP),

-- 채팅방 4
(4, 2, '재료 수급 문제 없나요?', CURRENT_TIMESTAMP),
(4, 3, '네, 모두 준비되어 있어요.', CURRENT_TIMESTAMP),
(4, 2, '좋아요.', CURRENT_TIMESTAMP),
(4, 3, '감사합니다.', CURRENT_TIMESTAMP),
(4, 2, '오늘 매출 기대돼요.', CURRENT_TIMESTAMP),

-- 채팅방 5
(5, 2, '신메뉴 출시 준비 되셨나요?', CURRENT_TIMESTAMP),
(5, 5, '네, 테스트 완료했습니다.', CURRENT_TIMESTAMP),
(5, 2, '좋아요.', CURRENT_TIMESTAMP),
(5, 5, '감사합니다!', CURRENT_TIMESTAMP),
(5, 2, '홍보도 준비하세요.', CURRENT_TIMESTAMP),

-- 채팅방 6
(6, 2, '가격 정책 어떻게 하실래요?', CURRENT_TIMESTAMP),
(6, 6, '기존대로 진행하려고 합니다.', CURRENT_TIMESTAMP),
(6, 2, '알겠습니다.', CURRENT_TIMESTAMP),
(6, 6, '감사합니다.', CURRENT_TIMESTAMP),
(6, 2, '이번 주 프로모션도 준비?', CURRENT_TIMESTAMP),

-- 채팅방 7
(7, 3, '손님 피드백 잘 받고 있나요?', CURRENT_TIMESTAMP),
(7, 4, '네, 모니터링 하고 있어요.', CURRENT_TIMESTAMP),
(7, 3, '좋아요.', CURRENT_TIMESTAMP),
(7, 4, '감사합니다.', CURRENT_TIMESTAMP),
(7, 3, '다음 회의 때 공유할게요.', CURRENT_TIMESTAMP),

-- 채팅방 8
(8, 3, '배달 일정 조정 가능할까요?', CURRENT_TIMESTAMP),
(8, 7, '네, 일정 맞췄습니다.', CURRENT_TIMESTAMP),
(8, 3, '좋아요.', CURRENT_TIMESTAMP),
(8, 7, '감사합니다.', CURRENT_TIMESTAMP),
(8, 3, '고객 응대도 준비했나요?', CURRENT_TIMESTAMP),

-- 채팅방 9
(9, 4, '이번 주 판매 목표 달성?', CURRENT_TIMESTAMP),
(9, 9, '거의 달성했습니다.', CURRENT_TIMESTAMP),
(9, 4, '좋네요.', CURRENT_TIMESTAMP),
(9, 9, '감사합니다.', CURRENT_TIMESTAMP),
(9, 4, '다음 주 계획도 공유해주세요.', CURRENT_TIMESTAMP),

-- 채팅방 10
(10, 4, '직원 교육은 어떻게 진행?', CURRENT_TIMESTAMP),
(10, 10, '정기 미팅과 체크리스트 사용 중.', CURRENT_TIMESTAMP),
(10, 4, '좋아요.', CURRENT_TIMESTAMP),
(10, 10, '감사합니다.', CURRENT_TIMESTAMP),
(10, 4, '보고서도 작성했나요?', CURRENT_TIMESTAMP),

-- 채팅방 11
(11, 5, '재고 관리 팁 공유해주세요.', CURRENT_TIMESTAMP),
(11, 6, '매일 체크하고 주문합니다.', CURRENT_TIMESTAMP),
(11, 5, '좋네요.', CURRENT_TIMESTAMP),
(11, 6, '감사합니다.', CURRENT_TIMESTAMP),
(11, 5, '이번 주도 잘 부탁드려요.', CURRENT_TIMESTAMP),

-- 채팅방 12
(12, 5, 'SNS 홍보 계획 있나요?', CURRENT_TIMESTAMP),
(12, 12, '주 2~3회 포스팅합니다.', CURRENT_TIMESTAMP),
(12, 5, '좋아요.', CURRENT_TIMESTAMP),
(12, 12, '감사합니다.', CURRENT_TIMESTAMP),
(12, 5, '고객 반응도 확인해요.', CURRENT_TIMESTAMP),

-- 채팅방 13
(13, 6, '신메뉴 피드백 받으셨나요?', CURRENT_TIMESTAMP),
(13, 7, '네, 만족도 높아요.', CURRENT_TIMESTAMP),
(13, 6, '좋네요.', CURRENT_TIMESTAMP),
(13, 7, '감사합니다.', CURRENT_TIMESTAMP),
(13, 6, '다음 회의 때 공유할게요.', CURRENT_TIMESTAMP),

-- 채팅방 14
(14, 5, '배달 수수료 어떻게 조정?', CURRENT_TIMESTAMP),
(14, 7, '기존 수준 유지 중이에요.', CURRENT_TIMESTAMP),
(14, 5, '알겠습니다.', CURRENT_TIMESTAMP),
(14, 7, '감사합니다.', CURRENT_TIMESTAMP),
(14, 5, '홍보는 잘 하고 계신가요?', CURRENT_TIMESTAMP),

-- 채팅방 15
(15, 5, '주말 인력 배치 준비?', CURRENT_TIMESTAMP),
(15, 8, '네, 1~2명 추가했습니다.', CURRENT_TIMESTAMP),
(15, 5, '좋아요.', CURRENT_TIMESTAMP),
(15, 8, '감사합니다.', CURRENT_TIMESTAMP),
(15, 5, '다음 주 계획도 참고해요.', CURRENT_TIMESTAMP),

-- 채팅방 16
(16, 6, '재고 확인 완료?', CURRENT_TIMESTAMP),
(16, 7, '네, 부족한 것 주문했어요.', CURRENT_TIMESTAMP),
(16, 6, '좋네요.', CURRENT_TIMESTAMP),
(16, 7, '감사합니다.', CURRENT_TIMESTAMP),
(16, 6, '이번 주도 체크해 주세요.', CURRENT_TIMESTAMP),

-- 채팅방 17
(17, 6, '프로모션 일정 확인?', CURRENT_TIMESTAMP),
(17, 8, '한 달에 한 번 진행 예정입니다.', CURRENT_TIMESTAMP),
(17, 6, '좋아요.', CURRENT_TIMESTAMP),
(17, 8, '감사합니다.', CURRENT_TIMESTAMP),
(17, 6, 'SNS 홍보도 준비했나요?', CURRENT_TIMESTAMP),

-- 채팅방 18
(18, 6, '배달 시간 단축 팁 공유해주세요.', CURRENT_TIMESTAMP),
(18, 9, '픽업 동선 최적화 중요합니다.', CURRENT_TIMESTAMP),
(18, 6, '좋네요.', CURRENT_TIMESTAMP),
(18, 9, '감사합니다.', CURRENT_TIMESTAMP),
(18, 6, '직원 교육도 체크했나요?', CURRENT_TIMESTAMP),

-- 채팅방 19
(19, 7, '주문 폭주 시 대응 방법?', CURRENT_TIMESTAMP),
(19, 8, '추가 인력 배치하고 안내 공지함.', CURRENT_TIMESTAMP),
(19, 7, '좋아요.', CURRENT_TIMESTAMP),
(19, 8, '감사합니다.', CURRENT_TIMESTAMP),
(19, 7, 'SNS 공지도 확인했나요?', CURRENT_TIMESTAMP),

-- 채팅방 20
(20, 7, '신메뉴 테스트 고객 선정?', CURRENT_TIMESTAMP),
(20, 9, '단골 고객 위주로 진행.', CURRENT_TIMESTAMP),
(20, 7, '좋네요.', CURRENT_TIMESTAMP),
(20, 9, '감사합니다.', CURRENT_TIMESTAMP),
(20, 7, '결과 공유 부탁드려요.', CURRENT_TIMESTAMP),

-- 채팅방 21
(21, 7, '배달 앱 광고 효율?', CURRENT_TIMESTAMP),
(21, 10, '시간대별 배너가 효과적이에요.', CURRENT_TIMESTAMP),
(21, 7, '좋아요.', CURRENT_TIMESTAMP),
(21, 10, 'ROI 확인도 했습니다.', CURRENT_TIMESTAMP),
(21, 7, '감사합니다.', CURRENT_TIMESTAMP),

-- 채팅방 22
(22, 8, '손님 재방문 유도 방법?', CURRENT_TIMESTAMP),
(22, 9, '적립금과 쿠폰 활용 좋아요.', CURRENT_TIMESTAMP),
(22, 8, '좋네요.', CURRENT_TIMESTAMP),
(22, 9, 'SNS 소식 공유도 필수입니다.', CURRENT_TIMESTAMP),
(22, 8, '감사합니다.', CURRENT_TIMESTAMP),

-- 채팅방 23
(23, 8, '주말 매출 예측 준비?', CURRENT_TIMESTAMP),
(23, 10, '평균 데이터 기반으로 준비했습니다.', CURRENT_TIMESTAMP),
(23, 8, '좋아요.', CURRENT_TIMESTAMP),
(23, 10, '감사합니다.', CURRENT_TIMESTAMP),
(23, 8, '다음 주 계획도 확인하세요.', CURRENT_TIMESTAMP),

-- 채팅방 24
(24, 8, 'SNS 홍보 주기는?', CURRENT_TIMESTAMP),
(24, 11, '주 2~3회 포스팅합니다.', CURRENT_TIMESTAMP),
(24, 8, '좋아요.', CURRENT_TIMESTAMP),
(24, 11, '감사합니다.', CURRENT_TIMESTAMP),
(24, 8, '반응 체크도 필수입니다.', CURRENT_TIMESTAMP),

-- 채팅방 25
(25, 9, '재료 신선도 관리?', CURRENT_TIMESTAMP),
(25, 10, '입고 확인 후 바로 냉장/냉동.', CURRENT_TIMESTAMP),
(25, 9, '좋아요.', CURRENT_TIMESTAMP),
(25, 10, '기록 남기면 편리합니다.', CURRENT_TIMESTAMP),
(25, 9, '감사합니다.', CURRENT_TIMESTAMP),

-- 채팅방 26
(26, 9, '고객 불만 대응 방법?', CURRENT_TIMESTAMP),
(26, 11, '빠른 답변과 보상 제공.', CURRENT_TIMESTAMP),
(26, 9, '좋네요.', CURRENT_TIMESTAMP),
(26, 11, '진행 기록도 남깁니다.', CURRENT_TIMESTAMP),
(26, 9, '감사합니다.', CURRENT_TIMESTAMP),

-- 채팅방 27
(27, 9, '신메뉴 출시 일정?', CURRENT_TIMESTAMP),
(27, 12, '월초 테스트 후 중순 공개 예정.', CURRENT_TIMESTAMP),
(27, 9, '좋아요.', CURRENT_TIMESTAMP),
(27, 12, '홍보와 시식 행사 병행합니다.', CURRENT_TIMESTAMP),
(27, 9, '감사합니다.', CURRENT_TIMESTAMP);



-- INSERT INTO article (id, restaurant_name, main_menu, userid, region_id, insert_at, state)
-- VALUES (1, 'OK Burger', '데리야끼 버거', 'abc123', NULL, '2024.05.24', '비공개'),
--        (2, 'OK Burger', '치즈버거', '오르미@화이팅.com', NULL, '2024.05.29', '공개'),
--        (3, '김기사식당', '제육볶음', 'zxcvbe', NULL, '2024.04.23', '공개'),
--        (4, '김기사식당', '순두부찌개', 'zxcvbe', NULL, '2024.04.22', '공개'),
--        (5, '맘스터치', '싸이순살버거', 'helloWorld', NULL, '2024.06.01', '비공개'),
--        (6, '맥도날드', '빅맥세트', 'burgerMan', NULL, '2024.07.11', '공개'),
--        (7, '롯데리아', '새우버거', 'foodie99', NULL, '2024.05.15', '공개'),
--        (8, '이삭토스트', '햄치즈토스트', 'qwerty', NULL, '2024.06.20', '비공개'),
--        (9, '한솥도시락', '치킨마요', 'happy123', NULL, '2024.04.10', '공개'),
--        (10, '홍콩반점', '짜장면', 'chopa', NULL, '2024.05.03', '비공개');