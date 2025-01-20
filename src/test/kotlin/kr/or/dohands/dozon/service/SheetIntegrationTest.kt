package kr.or.dohands.dozon.service

import kr.or.dohands.dozon.exp.service.*
import kr.or.dohands.dozon.quest.match.*
import kr.or.dohands.dozon.quest.service.HrEvaluationListService
import kr.or.dohands.dozon.quest.service.JobQuestsService
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import kr.or.dohands.dozon.quest.service.SwordProjectService
import kr.or.dohands.dozon.sheet.service.GoogleSheetsScheduler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class SheetIntegrationTest @Autowired constructor(
    private val sheetIntegrationScan: SheetIntegrationScan,
    private val jobQuestsService: JobQuestsService,
    private val giveExpJobQuest: GiveExpJobQuest,
    private val leaderQuestsService: LeaderQuestsService,
    private val giveExpLeaderQuest: GiveExpLeaderQuest,
    private val giveExpSwordProject: GiveExpSwordProject,
    private val swordProjectService:  SwordProjectService,
    private val hrEvaluationListService: HrEvaluationListService,
    private val giveExpHrEvaluation: GiveExpHrEvaluation
) {

    @Test
    fun `통합 스케줄링돌린다`() {
        sheetIntegrationScan.match()
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

    @org.junit.jupiter.api.Test
    fun `인사평가들을 로드하여 경험치를 일괄 지급한다`() {
        val list = hrEvaluationListService.findAll()
        val length = list.size

        for(i : Int in 0..<length){
            giveExpHrEvaluation.give(list.get(i).id)
        }
    }

    // 올해가 되어서 레벨업 진행 및 초기화 진행

    @Test
//    @Transactional
    fun `내년 초 레벨업을 진행한다`() {
        giveExpSwordProject.levelUp()
    }


}