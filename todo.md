[푸시알림 프로세스]

요청전에 퀘스트 아이디 갖고있고
1번 퀘스트  -> 퀘스트 아이디

요청할때 퀘스트 아이디 던져주고 -> 일괄로 이걸 expo 서버한테 요청을 내가 주면
알림이 쭈루룩 간다.

---

- 인사평가
  - 인사등급, 부여 경험치 가져가기

- 직무별 퀘스트
  - 부여 경험치 가져가기


[사용하지 않고 있는 테이블]

week-productivity

daily-productivity (이미 더미데이터 있음)

productivity-score

exp-history-week

**완성단계에 다다를때 필요없으면 삭제처리할 예정**

---

[x] - User 로그인인지 Admin 로그인인지 먼저 구별하는걸 작성해야할 것 같다.

[X] 메인
  - 올해 누적 경험치

[x]마이페이지 api
  - 필요 경험치 , 현재 누적 경험치 , 레벨

스프레드 시트 기능
[x] 리더부여 퀘스트 생성
[X] 리더부여 퀘스트 달성여부
[X] MAX/MEDIUM 입력시 계산
[X] 직무별 퀘스트 데이터 갱신

[x] - 구글 스프레드시트 추가 연동 및 실시간 감지 기능 , 데이터베이스에 일괄 수정되면서 주입되는 그런 기능들,,
  - 구글 드라이브 연동을 시도했는데 이게 -> 요청 들어오면 자연스럽게 수정이 되었다고 판단하고 -> 수정될만한 데이터들 한번 디비에서 쭉 새로고침 떄리기

[X] 경험치 현황 조회 화면에 해당하는 쿼리 작성
[X] 경험치 현황 조회 화면에 해당하는 service로직 작성 및 테스트
[-] - S3 세팅 , 이미지 박기 [폐기]

[확인하고 적은 요구사항]

[X]로그인
[X]나의 정보
[X]정보 확인
[X]정보 수정(비밀번호 변경)

[X] 경험치 현황
[X] 작년까지 누적 bar 수치
[X] 올해 누적 bar 수치

[X] 사번기준으로 검색
[X] 사번, 소속, 이름 , 현재 레벨 (유저)
[ ] 총 누적 경험치
[ ] 재작년까지 누적된 경험치
[ ] 작년까지 누적된 경험치
[X] 다음 레벨 달성에 필요한 총 경험치
[X] 다음 레벨 달성에 필요한 잔여 경험치


우리 팀 퀘스트
[ ] 퀘스트 달성 기록 그래프 수치
[ ] 경험치 달성 목록 확인

[X] 게시판 CRUD , API 작성
[ ] 계정 생성 (어드민)

[ ] 경험치 흭득과 게시글 알림
[ ] 경험치 충족 시 레벨 업 처리
[ ] 내년이 되는 것을 가정하여 작년 해의 exp 데이터를 exp-history-year로 마이그레이션처리 하며, exp 테이블의 총 수치들을 초기화, exp-history는 유지하는 테이블
job-quests, leader-quests, sword-project 는 초기화

---------------------------------------------------------------------------------------
필요한 알림이 무엇이 있는가?

알림
9:52
알림 리스트
신규 게시글 등록시 Puhs 알림
: 신규 게시글이 등록되었어요!! 확인해보세요
직무별 퀘스트
: 직무별 퀘스트 경험치가 들어왔어요!! 지금 확인해보세요.
- Medium
- Max
리더 부여 쿼스트
: 리더부여 퀘스트 경험치가 들어왔어요!! 지금 확인해보세요.
- Medium
- Max
전사프로젝트
: 전사 프로젝트 경험치가 들어왔어요!! 지금 확인해보세요
인사평가
: 인사 평가 경험치가 들어왔어요!! 지금 확인해보세요.
레벨업
: 레벨업을 했어요!! 지금 확인해보세요

------------------------------------------------------------------------------------

급한것.

내일 일어나면 비밀번호 변경쪽 테스트 한번 돌리기

구글 드라이브 트리거 감지 정상화 -> 스케줄링 으로 대체 (5초 , 쓰레드 1초대기)
매번 스프레드시트 갱신 확인

jobQUests, 평소 스케줄링시 업데이트 쿼리 제거 필요.. ,, -> 업데이트 쿼리 안나오게 유ㅎ지해보자.
leaderQuests, select 쿼리 줄여봐야되나..
속도를 더 올리고 싶다면 비동기 쓰레드 걸어주면 좋겠지만 지금으로도 충분하다..

부여 경험치 프로세스
푸시알림 프로세스

[X] 스프레드시트 key.json 인식안되는 문제 발생.. 내일 일어나서 처리하자.

---
[X]
부여 경험치 반영되었는지 확인 -> exp-history에서 확인
만약 이미 있다면 -> 부여 경험치 일치한지 확인 -> 아니라면 갱신
만약 없다면 추가하고 경험치 증가 (users, exp-history, exp)

부여 경험치를 갱신으로 먹일 수는 없고, 만약 부여경험치가 NUll이라면 대기하고 null이 아니라면 바로 부여하는식으로 들어가야될듯

달성현황 8개 데이터 정도만 메인에서 보여준다

---

기존의 시트 데이터 연동하는거는 일괄적으로 계속 실행을 하도록한다.
그리고 새로운 데이터가 추가되면 primary id 25 이런식으로 되어있을 것이다.
history를 보면 quest_id 25 를 찾는다. 없다. 그러면 지급한다. 있다. 이미 지급된것이다.

리더부여 퀘스트의 quest_id 라는 것이니까, 그쪽으로 select 걸어준다.
직무별 퀘스트의 quest_id라는 것이니까, 그쪽으로 select 걸어준다.

---

[x] 푸시알림 기능 


내년 되었을때 경험치 기록들 자동 초기화 및 과거 기록으로 새로 추가

인사평가 테스트 코드 , 엔티티 코드 수정 데이터 수정

[X] 경험치들 최대로 하는거

---

메인

[X]공지사항 notice:
[x]직무별, 리더부여, 전사 한개씩 (하나도 없을때만 카트기)
[x] 달성현황 퀘스트들은 4-6개정도 보여주면 될듯함
[x] 경험치 달성현황
[x] 관리자에서 데이터 조회 , 계정 생성 기능에 토큰 체크하는 기능 넣기
[x] 계정생성 중복 체크 하기
---


인사평가 기능 똑같이 한번더 나와서 테스트하면 끝
1년에 한번 이루어지는 초기화 과정을 테스트로 나중에 작성 .맨 후순위


[x] 회원 정보 수정

- quest/count 쪽에 clearnumber 2025로 찍히는 버그 픽스해야함

grade
date
questName
content
exp


questName

