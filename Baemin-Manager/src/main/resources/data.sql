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
--     abcD1@3
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
INSERT INTO chat_room (chat_room_id, user1_id, user2_id, updated_at)
VALUES (1, 1, 2, CURRENT_TIMESTAMP),
       (2, 1, 3, CURRENT_TIMESTAMP),
       (3, 1, 4, CURRENT_TIMESTAMP),
       (4, 2, 3, CURRENT_TIMESTAMP),
       (5, 2, 4, CURRENT_TIMESTAMP),
       (6, 2, 5, CURRENT_TIMESTAMP),
       (7, 3, 4, CURRENT_TIMESTAMP),
       (8, 3, 5, CURRENT_TIMESTAMP),
       (9, 3, 6, CURRENT_TIMESTAMP),
       (10, 4, 5, CURRENT_TIMESTAMP),
       (11, 4, 6, CURRENT_TIMESTAMP),
       (12, 4, 7, CURRENT_TIMESTAMP),
       (13, 5, 6, CURRENT_TIMESTAMP),
       (14, 5, 7, CURRENT_TIMESTAMP),
       (15, 5, 8, CURRENT_TIMESTAMP),
       (16, 6, 7, CURRENT_TIMESTAMP),
       (17, 6, 8, CURRENT_TIMESTAMP),
       (18, 6, 9, CURRENT_TIMESTAMP),
       (19, 7, 8, CURRENT_TIMESTAMP),
       (20, 7, 9, CURRENT_TIMESTAMP),
       (21, 7, 10, CURRENT_TIMESTAMP),
       (22, 8, 9, CURRENT_TIMESTAMP),
       (23, 8, 10, CURRENT_TIMESTAMP),
       (24, 8, 11, CURRENT_TIMESTAMP),
       (25, 9, 10, CURRENT_TIMESTAMP),
       (26, 9, 11, CURRENT_TIMESTAMP),
       (27, 9, 12, CURRENT_TIMESTAMP),
       (28, 10, 11, CURRENT_TIMESTAMP),
       (29, 10, 12, CURRENT_TIMESTAMP),
       (30, 10, 1, CURRENT_TIMESTAMP),
       (31, 11, 12, CURRENT_TIMESTAMP),
       (32, 11, 1, CURRENT_TIMESTAMP),
       (33, 11, 2, CURRENT_TIMESTAMP),
       (34, 12, 1, CURRENT_TIMESTAMP),
       (35, 12, 2, CURRENT_TIMESTAMP),
       (36, 12, 3, CURRENT_TIMESTAMP);

-- Chat
INSERT INTO chat (chat_id, chat_room_id, sender_id, message, created_at)
VALUES (1, 1, 1, '안녕하세요! 오늘 매출 어땠어요?', CURRENT_TIMESTAMP),
       (2, 1, 2, '안녕하세요, 오늘은 평소보다 조금 많았네요.', CURRENT_TIMESTAMP),
       (3, 1, 1, '오, 좋네요! 저도 손님이 꽤 있었어요.', CURRENT_TIMESTAMP),
       (4, 1, 2, '다행이네요. 이번 주 이벤트 준비하셨나요?', CURRENT_TIMESTAMP),
       (5, 1, 1, '네, 할인 쿠폰 준비했어요.', CURRENT_TIMESTAMP),

       (6, 2, 1, '요즘 재료값 많이 올랐죠?', CURRENT_TIMESTAMP),
       (7, 2, 3, '맞아요, 저희도 가격 조정 고민 중이에요.', CURRENT_TIMESTAMP),
       (8, 2, 1, '손님에게 부담 안 가게 해야죠.', CURRENT_TIMESTAMP),
       (9, 2, 3, '네, 메뉴 구성 다시 보려고 해요.', CURRENT_TIMESTAMP),
       (10, 2, 1, '좋은 아이디어네요.', CURRENT_TIMESTAMP),

       (11, 3, 1, '배달은 어떻게 관리하세요?', CURRENT_TIMESTAMP),
       (12, 3, 4, '저희는 자체 배달과 외부 앱 병행하고 있어요.', CURRENT_TIMESTAMP),
       (13, 3, 1, '아, 저도 고려해봐야겠네요.', CURRENT_TIMESTAMP),
       (14, 3, 4, '초반에는 힘들지만 점점 익숙해져요.', CURRENT_TIMESTAMP),
       (15, 3, 1, '좋아요, 참고할게요.', CURRENT_TIMESTAMP),

       (16, 4, 2, '이번 주 신메뉴 출시 계획 있으신가요?', CURRENT_TIMESTAMP),
       (17, 4, 3, '네, 다음 주부터 시작할 예정이에요.', CURRENT_TIMESTAMP),
       (18, 4, 2, '손님 반응이 기대되네요.', CURRENT_TIMESTAMP),
       (19, 4, 3, '맞아요, 홍보도 같이 진행해야죠.', CURRENT_TIMESTAMP),
       (20, 4, 2, '좋아요, 함께 홍보 아이디어 공유해요.', CURRENT_TIMESTAMP),

       (21, 5, 2, '재료 공급 업체 바꾸셨나요?', CURRENT_TIMESTAMP),
       (22, 5, 4, '네, 가격이 더 합리적이에요.', CURRENT_TIMESTAMP),
       (23, 5, 2, '그럼 저도 검토해봐야겠네요.', CURRENT_TIMESTAMP),
       (24, 5, 4, '샘플 받아보시면 좋을 것 같아요.', CURRENT_TIMESTAMP),
       (25, 5, 2, '좋아요, 연락 드릴게요.', CURRENT_TIMESTAMP),

       (26, 6, 2, '저번 프로모션은 효과 어땠나요?', CURRENT_TIMESTAMP),
       (27, 6, 5, '손님 반응 좋았어요. 매출도 조금 올랐고요.', CURRENT_TIMESTAMP),
       (28, 6, 2, '그럼 다음에도 비슷하게 진행해봐야겠네요.', CURRENT_TIMESTAMP),
       (29, 6, 5, '맞아요, 소셜 채널 홍보도 같이 하면 좋아요.', CURRENT_TIMESTAMP),
       (30, 6, 2, '좋은 의견 감사해요.', CURRENT_TIMESTAMP),

       (31, 7, 3, '주말 매출이 생각보다 높네요.', CURRENT_TIMESTAMP),
       (32, 7, 4, '맞아요, 손님이 많아서 준비 분량 늘렸어요.', CURRENT_TIMESTAMP),
       (33, 7, 3, '저도 참고해서 준비량 조절해야겠네요.', CURRENT_TIMESTAMP),
       (34, 7, 4, '네, 공유해드릴게요.', CURRENT_TIMESTAMP),
       (35, 7, 3, '감사합니다.', CURRENT_TIMESTAMP),

       (36, 8, 3, '최근 리뷰 반응 어떠신가요?', CURRENT_TIMESTAMP),
       (37, 8, 5, '좋아요, 특히 신규 메뉴에 관심 많아요.', CURRENT_TIMESTAMP),
       (38, 8, 3, '저도 확인해봐야겠네요.', CURRENT_TIMESTAMP),
       (39, 8, 5, 'SNS 홍보도 고려해보세요.', CURRENT_TIMESTAMP),
       (40, 8, 3, '좋아요, 해볼게요.', CURRENT_TIMESTAMP),

       (41, 9, 3, '이번 달 매출 목표 달성하셨나요?', CURRENT_TIMESTAMP),
       (42, 9, 6, '조금 부족하지만 거의 근접했어요.', CURRENT_TIMESTAMP),
       (43, 9, 3, '저도 비슷하네요.', CURRENT_TIMESTAMP),
       (44, 9, 6, '함께 아이디어 공유하면 좋을 것 같아요.', CURRENT_TIMESTAMP),
       (45, 9, 3, '좋아요, 회의 잡아볼까요?', CURRENT_TIMESTAMP),

       (46, 10, 4, '손님 피드백 모으는 방법 추천해주세요.', CURRENT_TIMESTAMP),
       (47, 10, 5, '저는 설문지와 SNS 댓글 확인을 병행해요.', CURRENT_TIMESTAMP),
       (48, 10, 4, '좋네요, 저도 적용해봐야겠어요.', CURRENT_TIMESTAMP),
       (49, 10, 5, '작은 혜택 주면 참여율 올라가요.', CURRENT_TIMESTAMP),
       (50, 10, 4, '좋아요, 감사합니다.', CURRENT_TIMESTAMP),

       (51, 11, 4, '직원 교육은 어떻게 진행하시나요?', CURRENT_TIMESTAMP),
       (52, 11, 6, '정기적으로 미팅하고 체크리스트 사용해요.', CURRENT_TIMESTAMP),
       (53, 11, 4, '좋아요, 저도 적용해볼게요.', CURRENT_TIMESTAMP),
       (54, 11, 6, '처음엔 힘들지만 점점 효율적이에요.', CURRENT_TIMESTAMP),
       (55, 11, 4, '알겠습니다.', CURRENT_TIMESTAMP),

       (56, 12, 4, '주문 취소 대응 어떻게 하세요?', CURRENT_TIMESTAMP),
       (57, 12, 7, '빠르게 안내하고 재주문 유도해요.', CURRENT_TIMESTAMP),
       (58, 12, 4, '좋아요, 저도 참고하겠습니다.', CURRENT_TIMESTAMP),
       (59, 12, 7, '손님 만족도가 좋아요.', CURRENT_TIMESTAMP),
       (60, 12, 4, '좋습니다.', CURRENT_TIMESTAMP),

       (61, 13, 5, '신메뉴 개발은 어떻게 진행하시나요?', CURRENT_TIMESTAMP),
       (62, 13, 6, '소규모 테스트 후 반응 확인해요.', CURRENT_TIMESTAMP),
       (63, 13, 5, '저도 시도해봐야겠네요.', CURRENT_TIMESTAMP),
       (64, 13, 6, 'SNS로 홍보하면 효과 좋아요.', CURRENT_TIMESTAMP),
       (65, 13, 5, '감사합니다.', CURRENT_TIMESTAMP),

       (66, 14, 5, '요즘 배달 수수료 부담 크시죠?', CURRENT_TIMESTAMP),
       (67, 14, 7, '맞아요, 조정 고민 중이에요.', CURRENT_TIMESTAMP),
       (68, 14, 5, '저도 다른 앱 검토해야겠네요.', CURRENT_TIMESTAMP),
       (69, 14, 7, '샘플 기간 설정하면 좋아요.', CURRENT_TIMESTAMP),
       (70, 14, 5, '좋아요, 적용해볼게요.', CURRENT_TIMESTAMP),

       (71, 15, 5, '주말 인력 배치 어떻게 하세요?', CURRENT_TIMESTAMP),
       (72, 15, 8, '평소보다 1~2명 추가해요.', CURRENT_TIMESTAMP),
       (73, 15, 5, '저도 참고해야겠네요.', CURRENT_TIMESTAMP),
       (74, 15, 8, '네, 미리 계획하면 효율적이에요.', CURRENT_TIMESTAMP),
       (75, 15, 5, '감사합니다.', CURRENT_TIMESTAMP),

       (76, 16, 6, '재고 관리는 어떻게 하시나요?', CURRENT_TIMESTAMP),
       (77, 16, 7, '매일 체크하고 부족하면 바로 주문해요.', CURRENT_TIMESTAMP),
       (78, 16, 6, '좋아요, 저도 적용해야겠네요.', CURRENT_TIMESTAMP),
       (79, 16, 7, '처음엔 귀찮지만 안정적이에요.', CURRENT_TIMESTAMP),
       (80, 16, 6, '좋습니다.', CURRENT_TIMESTAMP),

       (81, 17, 6, '프로모션 진행 주기는 어떻게 하시나요?', CURRENT_TIMESTAMP),
       (82, 17, 8, '한 달에 한 번 정도 진행해요.', CURRENT_TIMESTAMP),
       (83, 17, 6, '저도 시도해봐야겠네요.', CURRENT_TIMESTAMP),
       (84, 17, 8, 'SNS 홍보 함께 하면 효과 좋아요.', CURRENT_TIMESTAMP),
       (85, 17, 6, '좋아요, 참고할게요.', CURRENT_TIMESTAMP),

       (86, 18, 6, '배달 시간 단축 팁 있으신가요?', CURRENT_TIMESTAMP),
       (87, 18, 9, '픽업 동선 최적화하고 직원 교육 중요해요.', CURRENT_TIMESTAMP),
       (88, 18, 6, '좋네요, 저도 적용해볼게요.', CURRENT_TIMESTAMP),
       (89, 18, 9, '빠른 안내 문구 준비도 좋습니다.', CURRENT_TIMESTAMP),
       (90, 18, 6, '감사합니다.', CURRENT_TIMESTAMP),

       (91, 19, 7, '주문 폭주 시 대응은?', CURRENT_TIMESTAMP),
       (92, 19, 8, '추가 인력 배치하고 배달 시간 공지해요.', CURRENT_TIMESTAMP),
       (93, 19, 7, '좋아요, 저도 준비해야겠네요.', CURRENT_TIMESTAMP),
       (94, 19, 8, 'SNS 공지로 손님 불만 줄일 수 있어요.', CURRENT_TIMESTAMP),
       (95, 19, 7, '알겠습니다.', CURRENT_TIMESTAMP),

       (96, 20, 7, '신메뉴 테스트 고객 선정은?', CURRENT_TIMESTAMP),
       (97, 20, 9, '기존 단골 위주로 진행해요.', CURRENT_TIMESTAMP),
       (98, 20, 7, '좋네요, 저도 참고할게요.', CURRENT_TIMESTAMP),
       (99, 20, 9, '피드백 받으면 개선하기 좋아요.', CURRENT_TIMESTAMP),
       (100, 20, 7, '감사합니다.', CURRENT_TIMESTAMP),

       (101, 21, 7, '배달 앱 광고 효율은?', CURRENT_TIMESTAMP),
       (102, 21, 10, '시간대별 배너 노출이 효과적이에요.', CURRENT_TIMESTAMP),
       (103, 21, 7, '저도 적용해볼게요.', CURRENT_TIMESTAMP),
       (104, 21, 10, 'ROI 확인도 꼭 하세요.', CURRENT_TIMESTAMP),
       (105, 21, 7, '좋아요.', CURRENT_TIMESTAMP),

       (106, 22, 8, '손님 재방문 유도 팁은?', CURRENT_TIMESTAMP),
       (107, 22, 9, '적립금과 쿠폰 활용 좋아요.', CURRENT_TIMESTAMP),
       (108, 22, 8, '저도 적용해봐야겠네요.', CURRENT_TIMESTAMP),
       (109, 22, 9, 'SNS 소식 공유도 도움이 돼요.', CURRENT_TIMESTAMP),
       (110, 22, 8, '좋아요, 감사합니다.', CURRENT_TIMESTAMP),

       (111, 23, 8, '주말 매출 예측은 어떻게 하시나요?', CURRENT_TIMESTAMP),
       (112, 23, 10, '평균 데이터 기반으로 미리 준비해요.', CURRENT_TIMESTAMP),
       (113, 23, 8, '저도 참고하겠습니다.', CURRENT_TIMESTAMP),
       (114, 23, 10, '직원 일정 조정도 함께 계획하세요.', CURRENT_TIMESTAMP),
       (115, 23, 8, '좋아요.', CURRENT_TIMESTAMP),

       (116, 24, 8, 'SNS 홍보 주기는?', CURRENT_TIMESTAMP),
       (117, 24, 11, '주 2~3회 정도 포스팅해요.', CURRENT_TIMESTAMP),
       (118, 24, 8, '저도 따라해볼게요.', CURRENT_TIMESTAMP),
       (119, 24, 11, '반응 확인도 필수입니다.', CURRENT_TIMESTAMP),
       (120, 24, 8, '좋아요, 참고할게요.', CURRENT_TIMESTAMP),

       (121, 25, 9, '재료 신선도 관리 팁?', CURRENT_TIMESTAMP),
       (122, 25, 10, '입고 확인 후 바로 냉장/냉동 처리해요.', CURRENT_TIMESTAMP),
       (123, 25, 9, '좋아요, 저도 적용해봐야겠네요.', CURRENT_TIMESTAMP),
       (124, 25, 10, '기록 남기면 추적하기 편해요.', CURRENT_TIMESTAMP),
       (125, 25, 9, '감사합니다.', CURRENT_TIMESTAMP),

       (126, 26, 9, '고객 불만 대응 노하우?', CURRENT_TIMESTAMP),
       (127, 26, 11, '빠른 답변과 보상 제공 중요해요.', CURRENT_TIMESTAMP),
       (128, 26, 9, '저도 적용해야겠네요.', CURRENT_TIMESTAMP),
       (129, 26, 11, '진행 기록 남기는 것도 좋아요.', CURRENT_TIMESTAMP),
       (130, 26, 9, '좋습니다.', CURRENT_TIMESTAMP),

       (131, 27, 9, '신메뉴 출시 일정 어떻게 잡으시나요?', CURRENT_TIMESTAMP),
       (132, 27, 12, '월초에 테스트 후 중순에 공개해요.', CURRENT_TIMESTAMP),
       (133, 27, 9, '저도 계획 참고하겠습니다.', CURRENT_TIMESTAMP),
       (134, 27, 12, '홍보와 시식 행사 병행하면 효과적이에요.', CURRENT_TIMESTAMP),
       (135, 27, 9, '좋아요, 감사합니다.', CURRENT_TIMESTAMP),

       (136, 28, 10, '배달 팁 적정 금액은?', CURRENT_TIMESTAMP),
       (137, 28, 11, '지역 평균 참고하고 손님 의견도 반영해요.', CURRENT_TIMESTAMP),
       (138, 28, 10, '저도 적용해봐야겠네요.', CURRENT_TIMESTAMP),
       (139, 28, 11, '홍보 문구도 조정하면 좋아요.', CURRENT_TIMESTAMP),
       (140, 28, 10, '좋아요.', CURRENT_TIMESTAMP),

       (141, 29, 10, '주말 인력 배치 방법?', CURRENT_TIMESTAMP),
       (142, 29, 12, '예상 주문량 기준으로 배치해요.', CURRENT_TIMESTAMP),
       (143, 29, 10, '저도 참고하겠습니다.', CURRENT_TIMESTAMP),
       (144, 29, 12, '초과 인력은 대기시켜요.', CURRENT_TIMESTAMP),
       (145, 29, 10, '좋아요.', CURRENT_TIMESTAMP),

       (146, 30, 10, '직원 교육 자료 공유 가능할까요?', CURRENT_TIMESTAMP),
       (147, 30, 1, '네, 자료 PDF로 공유 가능합니다.', CURRENT_TIMESTAMP),
       (148, 30, 10, '감사합니다, 활용할게요.', CURRENT_TIMESTAMP),
       (149, 30, 1, '추가 질문 있으면 알려주세요.', CURRENT_TIMESTAMP),
       (150, 30, 10, '좋아요.', CURRENT_TIMESTAMP),

       (151, 31, 11, 'SNS 이벤트 진행해보셨나요?', CURRENT_TIMESTAMP),
       (152, 31, 12, '네, 팔로워 참여율이 꽤 높아요.', CURRENT_TIMESTAMP),
       (153, 31, 11, '저도 계획 참고하겠습니다.', CURRENT_TIMESTAMP),
       (154, 31, 12, '보상은 소정으로 제공하면 좋아요.', CURRENT_TIMESTAMP),
       (155, 31, 11, '좋아요.', CURRENT_TIMESTAMP),

       (156, 32, 11, '배달 앱 신규 기능 활용하셨나요?', CURRENT_TIMESTAMP),
       (157, 32, 1, '네, 배달 추적 기능이 편리해요.', CURRENT_TIMESTAMP),
       (158, 32, 11, '좋아요, 저도 시도해볼게요.', CURRENT_TIMESTAMP),
       (159, 32, 1, '알림 메시지도 활용하세요.', CURRENT_TIMESTAMP),
       (160, 32, 11, '감사합니다.', CURRENT_TIMESTAMP),

       (161, 33, 11, '주말 메뉴 준비 팁?', CURRENT_TIMESTAMP),
       (162, 33, 2, '인력과 재료 미리 준비해요.', CURRENT_TIMESTAMP),
       (163, 33, 11, '좋아요, 참고하겠습니다.', CURRENT_TIMESTAMP),
       (164, 33, 2, '손님 흐름에 맞춰 조정하면 좋아요.', CURRENT_TIMESTAMP),
       (165, 33, 11, '감사합니다.', CURRENT_TIMESTAMP),

       (166, 34, 12, 'SNS 홍보 이미지 제작은?', CURRENT_TIMESTAMP),
       (167, 34, 1, '간단하게 Canva로 제작하고 있어요.', CURRENT_TIMESTAMP),
       (168, 34, 12, '좋아요, 저도 시도해볼게요.', CURRENT_TIMESTAMP),
       (169, 34, 1, '포맷 공유드릴게요.', CURRENT_TIMESTAMP),
       (170, 34, 12, '감사합니다.', CURRENT_TIMESTAMP),

       (171, 35, 12, '고객 리뷰 관리 방법?', CURRENT_TIMESTAMP),
       (172, 35, 2, '좋은 리뷰는 SNS에 공유하고, 불만은 바로 대응해요.', CURRENT_TIMESTAMP),
       (173, 35, 12, '저도 적용해야겠네요.', CURRENT_TIMESTAMP),
       (174, 35, 2, '정기 모니터링 필수입니다.', CURRENT_TIMESTAMP),
       (175, 35, 12, '좋아요.', CURRENT_TIMESTAMP),

       (176, 36, 12, '신메뉴 피드백은 어떻게 수집하시나요?', CURRENT_TIMESTAMP),
       (177, 36, 3, '직접 방문 손님과 SNS 설문으로 진행해요.', CURRENT_TIMESTAMP),
       (178, 36, 12, '좋아요, 저도 시도해보겠습니다.', CURRENT_TIMESTAMP),
       (179, 36, 3, '작은 이벤트 함께 하면 참여율 올라가요.', CURRENT_TIMESTAMP),
       (180, 36, 12, '감사합니다.', CURRENT_TIMESTAMP);


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