INSERT INTO career (name, createDate) VALUES ("음성 1센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("음성 2센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("용인백암센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("남양주센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("파주센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("사업기획팀", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("그로스팀", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("CX팀", "2023-01-01");

INSERT INTO part (name, updateDate) VALUES (1, "2025-01-01");
INSERT INTO part (name, updateDate) VALUES (2, "2025-01-01");

INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F1-Ⅰ', 0, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F1-Ⅱ', 13500, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F2-Ⅰ', 27000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F2-Ⅱ', 39000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F2-Ⅲ', 51000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F3-Ⅰ', 63000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F3-Ⅱ', 78000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F3-Ⅲ', 93000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F4-Ⅰ', 108000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F4-Ⅱ', 126000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F4-Ⅲ', 144000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('F5', 162000, 'F');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('B1', 0, 'B');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('B2', 24000, 'B');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('B3', 52000, 'B');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('B4', 78000, 'B');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('B5', 117000, 'B');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('B6', 169000, 'B');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('G1', 0, 'G');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('G2', 24000, 'G');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('G3', 52000, 'G');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('G4', 78000, 'G');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('G5', 117000, 'G');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('G6', 169000, 'G');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('T1', 0, 'T');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('T2', 1, 'T');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('T3', 2, 'T');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('T4', 3, 'T');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('T5', 4, 'T');
INSERT INTO `level-exp-type` (level, exp, type) VALUES ('T6', 5, 'T');

INSERT INTO `hr-evaluation` (grade, exp) VALUES ('S등급', 6500);
INSERT INTO `hr-evaluation` (grade, exp) VALUES ('A등급', 4500);
INSERT INTO `hr-evaluation` (grade, exp) VALUES ('B등급', 3000);
INSERT INTO `hr-evaluation` (grade, exp) VALUES ('C등급', 1500);
INSERT INTO `hr-evaluation` (grade, exp) VALUES ('D등급', 0);

INSERT INTO users (number, name, joinDate, career_name, id, level_level, part_name, password, newPassword, exp, userType, pushToken) VALUES (2021010101, "장우영", "2021-01-01", "음성 1센터", "equal", "F1-I", 1, "1111", "equal1234", 0, 1, "");
INSERT INTO users (number, name, joinDate, career_name, id, level_level, part_name, password, newPassword, exp, userType, pushToken) VALUES (2023010101, "김민수", "2023-01-01", "음성 1센터", "minsukim", "F1-Ⅰ", 1, "1111", NULL, 13400, 0, "ExponentPushToken[ig62iLPv3vHGnrZIORdxUN]");
INSERT INTO users (number, name, joinDate, career_name, id, level_level, part_name, password, newPassword, exp, userType, pushToken) VALUES (2021030101, "허재민", "2021-03-01", "음성 1센터", "jaminheo", "F1-Ⅱ", 1, "1111", NULL, 17000, 0, "");
INSERT INTO users (number, name, joinDate, career_name, id, level_level, part_name, password, newPassword, exp, userType, pushToken) VALUES (2023010102, "성주현", "2023-01-01", "음성 1센터", "juhyeonsung", "F1-Ⅰ", 1, "1111", NULL, 6000,0, "");
INSERT INTO users (number, name, joinDate, career_name, id, level_level, part_name, password, newPassword, exp, userType, pushToken) VALUES (2018020101, "조선우", "2018-02-01", "음성 2센터", "seonwoocho", "F2-Ⅰ", 1, "1111", NULL, 37000,0, "");
INSERT INTO users (number, name, joinDate, career_name, id, level_level, part_name, password, newPassword, exp, userType, pushToken) VALUES (2022080101, "심유나", "2023-10-01", "음성 2센터", "yoonasim", "F1-Ⅰ", 1, "1111", NULL, 3000,0, "");
INSERT INTO users (number, name, joinDate, career_name, id, level_level, part_name, password, newPassword, exp, userType, pushToken) VALUES (2024090101, "양소영", "2022-09-01", "사업기획팀", "soyoungyang", "B1", 1, "1111", NULL, 17000,0, "");
INSERT INTO users (number, name, joinDate, career_name, id, level_level, part_name, password, newPassword, exp, userType, pushToken) VALUES (2019090101, "김지수", "2019-09-01", "사업기획팀", "jisookim", "B2", 2, "1111", NULL, 40000,0, "");

INSERT INTO exp (number_number, totalExp, firstEvaluation, secondEvaluation, jobQuests, leaderQuests, swordProject, year) VALUES (2023010101, 13400, 1500, 3000, 2640, 517, 0, 2024);
INSERT INTO exp (number_number, totalExp, firstEvaluation, secondEvaluation, jobQuests, leaderQuests, swordProject, year) VALUES (2021030101, 10690, 3000, 4500, 2640, 550, 0, 2024);
INSERT INTO exp (number_number, totalExp, firstEvaluation, secondEvaluation, jobQuests, leaderQuests, swordProject, year) VALUES (2023010102, 7523, 1500, 3000, 2640, 383, 0, 2024);
INSERT INTO exp (number_number, totalExp, firstEvaluation, secondEvaluation, jobQuests, leaderQuests, swordProject, year) VALUES (2018020101, 6000, 3000, 3000, 0, 0, 0, 2024);
INSERT INTO exp (number_number, totalExp, firstEvaluation, secondEvaluation, jobQuests, leaderQuests, swordProject, year) VALUES (2022080101, 7500, 4500, 3000, 0, 0, 0, 2024);
INSERT INTO exp (number_number, totalExp, firstEvaluation, secondEvaluation, jobQuests, leaderQuests, swordProject, year) VALUES (2024090101, 3000, 1500, 1500, 0, 0, 0, 2024);
INSERT INTO exp (number_number, totalExp, firstEvaluation, secondEvaluation, jobQuests, leaderQuests, swordProject, year) VALUES (2019090101, 4500, 3000, 1500, 0, 0, 0, 2024);

-- 전년도 경험치
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2023010101, 2023, 5000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2021030101, 2023, 6000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2021030101, 2023, 7000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2021030101, 2022, 4000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2023010102, 2023, 6000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2018020101, 2023, 7000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2018020101, 2022, 5000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2018020101, 2021, 6000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2018020101, 2020, 8000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2018020101, 2019, 7000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2018020101, 2018, 4000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2022080101, 2023, 3000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2024090101, 2023, 11000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2024090101, 2022, 6000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2019090101, 2023, 12000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2019090101, 2022, 10000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2019090101, 2021, 7000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2019090101, 2020, 7000);
INSERT INTO `exp-history-year` (number, year, exp) VALUES (2019090101, 2019, 4000);

INSERT INTO `productivity-score` (grade, score) VALUES ("MAX", 80);
INSERT INTO `productivity-score` (grade, score) VALUES ("MEDIUM", 40);




INSERT INTO `posts` (title, content, date) VALUES ('AAA 프로젝트 신설 (경험치 500 do, 신청 마감 ~10/31)', '새로운 프로젝트 경험치를 획득할 기회! 지금 신청하세요.', CURRENT_DATE);

INSERT INTO `posts` (title, content, date) VALUES ('잡초이스 공고(신청 마감 ~11/20)', '다양한 직무 기회와 정보를 확인하고 지원하세요.', CURRENT_DATE);

INSERT INTO `posts` (title, content, date) VALUES ( '팀장 모집 공고(경험치 300 do, 신청 마감 ~12/05)', '팀장 역할을 수행하며 새로운 도전을 시작해보세요.', CURRENT_DATE);

INSERT INTO `posts` (title, content, date) VALUES ( '겨울 세미나 개최 안내(신청 마감 ~12/15)', '겨울 세미나에서 최신 트렌드와 기술을 배우고 네트워킹하세요.', CURRENT_DATE);

INSERT INTO `posts` (title, content, date) VALUES ( '커뮤니티 리더 선발 공고(경험치 200 do, 신청 마감 ~01/10)', '리더십을 발휘하고 커뮤니티를 성장시킬 기회를 잡으세요.', CURRENT_DATE);

INSERT INTO `posts` (title, content, date) VALUES ( '신규 워크숍 참여자 모집(신청 마감 ~01/20)', '창의적인 아이디어와 팀워크를 발휘할 수 있는 워크숍에 참여하세요.', CURRENT_DATE);





-- 경험치 기록
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '상반기 인사평가', '2023-06-30', 2023, 1500, 'C등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '상반기 인사평가', '2023-06-30', 2023, 3000, 'B등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '상반기 인사평가', '2023-06-30', 2023, 1500, 'C등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2018020101, '상반기 인사평가', '2023-06-30', 2023, 3000, 'B등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2022080101, '상반기 인사평가', '2023-06-30', 2023, 4500, 'A등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2024090101, '상반기 인사평가', '2023-06-30', 2023, 1500, 'C등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2019090101, '상반기 인사평가', '2023-06-30', 2023, 3000, 'B등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '하반기 인사평가', '2023-12-31', 2023, 3000, 'B등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '하반기 인사평가', '2023-12-31', 2023, 4500, 'A등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '하반기 인사평가', '2023-12-31', 2023, 3000, 'B등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2018020101, '하반기 인사평가', '2023-12-31', 2023, 3000, 'B등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2022080101, '하반기 인사평가', '2023-12-31', 2023, 3000, 'B등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2024090101, '하반기 인사평가', '2023-12-31', 2023, 1500, 'C등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2019090101, '하반기 인사평가', '2023-12-31', 2023, 1500, 'C등급');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '리더부여 퀘스트', '2023-01-15', 2023, 100, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '리더부여 퀘스트', '2023-01-20', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '리더부여 퀘스트', '2023-02-10', 2023, 67, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '리더부여 퀘스트', '2023-02-25', 2023, 33, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '리더부여 퀘스트', '2023-03-05', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '리더부여 퀘스트', '2023-03-20', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '리더부여 퀘스트', '2023-04-10', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '리더부여 퀘스트', '2023-04-15', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '리더부여 퀘스트', '2023-05-10', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '리더부여 퀘스트', '2023-05-20', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '리더부여 퀘스트', '2023-06-05', 2023, 100, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '리더부여 퀘스트', '2023-07-15', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '리더부여 퀘스트', '2023-08-10', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '리더부여 퀘스트', '2023-08-25', 2023, 100, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '리더부여 퀘스트', '2023-09-15', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '리더부여 퀘스트', '2023-10-05', 2023, 100, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '리더부여 퀘스트', '2023-10-15', 2023, 100, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '리더부여 퀘스트', '2023-10-25', 2023, 100, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '리더부여 퀘스트', '2023-11-10', 2023, 100, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '리더부여 퀘스트', '2023-12-05', 2023, 50, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '리더부여 퀘스트', '2023-12-15', 2023, 67, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '리더부여 퀘스트', '2023-12-20', 2023, 100, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '전사프로젝트', '2023-03-15', 2023, 1200, '');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '전사프로젝트', '2023-04-20', 2023, 1500, '');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '전사프로젝트', '2023-05-25', 2023, 1800, '');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2018020101, '전사프로젝트', '2023-06-10', 2023, 1400, '');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2022080101, '전사프로젝트', '2023-07-05', 2023, 1600, '');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '직무별 퀘스트', '2023-01-15', 2023, 0, '');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '직무별 퀘스트', '2023-02-10', 2023, 80, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '직무별 퀘스트', '2023-03-12', 2023, 40, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '직무별 퀘스트', '2023-04-08', 2023, 40, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '직무별 퀘스트', '2023-05-15', 2023, 80, 'MAX');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '직무별 퀘스트', '2023-06-25', 2023, 40, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '직무별 퀘스트', '2023-07-14', 2023, 40, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '직무별 퀘스트', '2023-08-07', 2023, 40, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '직무별 퀘스트', '2023-09-03', 2023, 0, '');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010101, '직무별 퀘스트', '2023-10-10', 2023, 0, '');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2021030101, '직무별 퀘스트', '2023-11-15', 2023, 40, 'MEDIUM');
-- INSERT INTO `exp-history` (number, questType, updateDate, year, exp, grade) VALUES (2023010102, '직무별 퀘스트', '2023-12-01', 2023, 40, 'MEDIUM');
