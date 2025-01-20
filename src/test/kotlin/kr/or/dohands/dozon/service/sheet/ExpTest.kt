package kr.or.dohands.dozon.service.sheet

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.be
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.exp.service.GiveExpJobQuest
import kr.or.dohands.dozon.exp.service.GiveExpLeaderQuest
import kr.or.dohands.dozon.exp.service.GiveExpSwordProject
import kr.or.dohands.dozon.quest.match.HrEvaluationMatch
import kr.or.dohands.dozon.quest.match.SheetIntegrationScan
import kr.or.dohands.dozon.quest.service.HrEvaluationListService
import kr.or.dohands.dozon.quest.service.JobQuestsService
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import kr.or.dohands.dozon.quest.service.SwordProjectService
import kr.or.dohands.dozon.user.service.UserExpProvider
import kr.or.dohands.dozon.user.service.UserService
import org.assertj.core.api.Assertions
import org.joda.time.LocalDateTime
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.text.SimpleDateFormat
import kotlin.test.Test

@SpringBootTest
class ExpTest @Autowired constructor(
    private val expHistoryService: ExpHistoryService,
    private val userService: UserService,
    private val userExpProvider: UserExpProvider,
    private val entityManager: EntityManager,
    private val leaderQuestsService: LeaderQuestsService,
    private val jobQuestsService: JobQuestsService,
    private val swordProjectService: SwordProjectService,
    private val giveExpJobQuest: GiveExpJobQuest,
    private val giveExpLeaderQuest: GiveExpLeaderQuest,
    private val giveExpSwordProject: GiveExpSwordProject,
    private val sheetIntegrationScan: SheetIntegrationScan,
) {

    @BeforeEach
    fun `사전스캔한다`(){
        sheetIntegrationScan.match()
    }

    @Test
    @Transactional
    fun `특정 퀘스트를 달성하여 부여 경험치를 부여한다`() {

        // 부여 받은 경험치 인지 확인하여 중복 부여 경험치를 방지
        val id = "minsukim"
        val year = 2023
        val exp = 30000L
        val number = 2023010101L
        val updateDate = SimpleDateFormat("yyyy-MM-dd").parse("2024-12-31")
        val grade = "MEDIUM"
        val questType = "직무별 퀘스트"

        val before = userService.findUserByNumber(number)

        entityManager.detach(before)


        // 중복 부여 경험치인지 검증을 취하고 만약에 존재하지 않던 내용이면 바로 지급을 시도한다.
        // 만약 존재한다면 무시하고 넘어가기
//        if(questType == "직무별 퀘스트"){
//            expHistoryService.validateJobQuests()
//        }else if(questType == "리더부여 퀘스트"){
//            expHistoryService.validateLeaderQuests()
//        }

        val leaderQuest = leaderQuestsService.findAll()

//        if(leaderQuest.get(i).exp ==?) // 0 , null?
        // 그냥 퀘스트들 쫙 긁어서 던지고 그거 그냥 주면 questId별로 훑어서 진행
        leaderQuest
        // year , exp , number, updateDate, grade, questType

        val history = expHistoryService.write(year, exp, number, java.time.LocalDateTime.now(), grade, questType, 0)
        userExpProvider.giveExp(exp+before.exp, number)
        val result = userService.findUserByNumber(number)
        Assertions.assertThat(result.exp).isEqualTo(exp + before.exp)

        // when
        // 부여경험치가 같은지 매칭 아니라면 갱신
        // 없다면 경험치 기록을 추가하고 부여 경험치 부여 (users, exp, exp-history)
        // then
        Assertions.assertThat(history).isNotNull
    }

    @Test
//    @Transactional
    fun `직무별 퀘스트들을 로드하여 경험치를 일괄 지급한다`() {
        val list = jobQuestsService.findAll()
        val length = list.size
        println("!!!"+ length)

        for(i : Int in 0..<length){
            giveExpJobQuest.give(list.get(i).id)
        }

    }

    @Test
//    @Transactional
    fun `리더부여 퀘스트들을 로드하여 경험치를 일괄 지급한다`() {
        val list = leaderQuestsService.findAll()
        val length = list.size

        for(i : Int in 0..<length){
            giveExpLeaderQuest.give(list.get(i).id)
        }
    }

    @Test
//    @Transactional
    fun `전사 프로젝트들을 로드하여 경험치를 일괄 지급한다`() {
        val list = swordProjectService.findAll()
        val length = list.size

        for(i : Int in 0..<length){
            giveExpSwordProject.give(list.get(i).id)
        }
    }



}
