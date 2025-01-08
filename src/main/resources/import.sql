INSERT INTO career (name, createDate) VALUES ("음성 1센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("음성 2센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("용인백암센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("남양주센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("파주센터", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("사업기획팀", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("그로스팀", "2023-01-01");
INSERT INTO career (name, createDate) VALUES ("CX팀", "2023-01-01");

INSERT INTO team (name, updateDate) VALUES (1, "2025-01-01");
INSERT INTO team (name, updateDate) VALUES (2, "2025-01-01");

INSERT INTO `level-exp-type` (level, exp) VALUES ('F1-Ⅰ', 0);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F1-Ⅱ', 13500);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F2-Ⅰ', 27000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F2-Ⅱ', 39000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F2-Ⅲ', 51000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F3-Ⅰ', 63000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F3-Ⅱ', 78000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F3-Ⅲ', 93000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F4-Ⅰ', 108000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F4-Ⅱ', 126000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F4-Ⅲ', 144000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('F5', 162000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('B1', 0);
INSERT INTO `level-exp-type` (level, exp) VALUES ('B2', 24000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('B3', 52000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('B4', 78000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('B5', 117000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('B6', 169000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('G1', 0);
INSERT INTO `level-exp-type` (level, exp) VALUES ('G2', 24000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('G3', 52000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('G4', 78000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('G5', 117000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('G6', 169000);
INSERT INTO `level-exp-type` (level, exp) VALUES ('T1', 0);
INSERT INTO `level-exp-type` (level, exp) VALUES ('T2', 1);
INSERT INTO `level-exp-type` (level, exp) VALUES ('T3', 2);
INSERT INTO `level-exp-type` (level, exp) VALUES ('T4', 3);
INSERT INTO `level-exp-type` (level, exp) VALUES ('T5', 4);
INSERT INTO `level-exp-type` (level, exp) VALUES ('T6', 5);

INSERT INTO `hr-evaluation` (grade, exp) VALUES ('S등급', 6500);
INSERT INTO `hr-evaluation` (grade, exp) VALUES ('A등급', 4500);
INSERT INTO `hr-evaluation` (grade, exp) VALUES ('B등급', 3000);
INSERT INTO `hr-evaluation` (grade, exp) VALUES ('C등급', 1500);
INSERT INTO `hr-evaluation` (grade, exp) VALUES ('D등급', 0);

INSERT INTO users (number, name, joinDate, career_name, id, level_level, team_name, password, newPassword, exp, userType) VALUES (2021010101, "장우영", "2021-01-01", "음성 1센터", "equal", "F1-I", 1, 1111, "equal1234", 0, 1);
INSERT INTO users (number, name, joinDate, career_name, id, level_level, team_name, password, newPassword, exp, userType) VALUES (2023010101, "김민수", "2023-01-01", "음성 1센터", "minsukim", "F1-Ⅰ", 1, 1111, NULL, 5000, 0);
INSERT INTO users (number, name, joinDate, career_name, id, level_level, team_name, password, newPassword, exp, userType) VALUES (2021030101, "허재민", "2021-03-01", "음성 1센터", "jaminheo", "F1-Ⅱ", 1, 1111, NULL, 17000, 0);
INSERT INTO users (number, name, joinDate, career_name, id, level_level, team_name, password, newPassword, exp, userType) VALUES (2023010102, "성주현", "2023-01-01", "음성 1센터", "juhyeonsung", "F1-Ⅰ", 1, 1111, NULL, 6000,0);
INSERT INTO users (number, name, joinDate, career_name, id, level_level, team_name, password, newPassword, exp, userType) VALUES (2018020101, "조선우", "2018-02-01", "음성 2센터", "seonwoocho", "F2-Ⅰ", 1, 1111, NULL, 37000,0);
INSERT INTO users (number, name, joinDate, career_name, id, level_level, team_name, password, newPassword, exp, userType) VALUES (2022080101, "심유나", "2023-10-01", "음성 2센터", "yoonasim", "F1-Ⅰ", 1, 1111, NULL, 3000,0);
INSERT INTO users (number, name, joinDate, career_name, id, level_level, team_name, password, newPassword, exp, userType) VALUES (2024090101, "양소영", "2022-09-01", "사업기획팀", "soyoungyang", "B1", 1, 1111, NULL, 17000,0);
INSERT INTO users (number, name, joinDate, career_name, id, level_level, team_name, password, newPassword, exp, userType) VALUES (2019090101, "김지수", "2019-09-01", "사업기획팀", "jisookim", "B2", 2, 1111, NULL, 40000,0);

INSERT INTO exp (number_number, totalExp, firstEvaluation, secondEvaluation, jobQuests, leaderQuests, swordProject, year) VALUES (2023010101, 7657, 1500, 3000, 2640, 517, 0, 2024);
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

# INSERT INTO `proeuctivity-score` (grade, score) VALUES ("MAX", 80);
# INSERT INTO `proeuctivity-score` (grade, score) VALUES ("MEDIUM", 40);

INSERT INTO `daily-productivity` (month, week, date, day, price, laborCosts, designFee, employSalaries, retirementBenefits, socialInsurance) VALUES (1, 1, '25-1-1', 'Wed', 10, 4, 1, 1, 1, 1);
INSERT INTO `daily-productivity` (month, week, date, day, price, laborCosts, designFee, employSalaries, retirementBenefits, socialInsurance) VALUES (1, 1, '25-1-2', 'Thu', 20, 4, 1, 1, 1, 1);
INSERT INTO `daily-productivity` (month, week, date, day, price, laborCosts, designFee, employSalaries, retirementBenefits, socialInsurance) VALUES (1, 1, '25-1-3', 'Fri', 30, 5, 2, 1, 1, 1);
INSERT INTO `daily-productivity` (month, week, date, day, price, laborCosts, designFee, employSalaries, retirementBenefits, socialInsurance) VALUES (1, 1, '25-1-4', 'Sat', 10, 5, 2, 1, 1, 1);
INSERT INTO `daily-productivity` (month, week, date, day, price, laborCosts, designFee, employSalaries, retirementBenefits, socialInsurance) VALUES (1, 1, '25-1-5', 'Sun', 20, 5, 2, 1, 1, 1);
INSERT INTO `daily-productivity` (month, week, date, day, price, laborCosts, designFee, employSalaries, retirementBenefits, socialInsurance) VALUES (1, 2, '25-1-6', 'Mon', 30, 5, 2, 1, 1, 1);
INSERT INTO `daily-productivity` (month, week, date, day, price, laborCosts, designFee, employSalaries, retirementBenefits, socialInsurance) VALUES (1, 2, '25-1-7', 'Tue', 30, 5, 2, 1, 1, 1);
INSERT INTO `daily-productivity` (month, week, date, day, price, laborCosts, designFee, employSalaries, retirementBenefits, socialInsurance) VALUES (1, 2, '25-1-8', 'Wed', 30, 5, 2, 1, 1, 1);
