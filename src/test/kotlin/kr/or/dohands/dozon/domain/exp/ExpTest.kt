package kr.or.dohands.dozon.domain.exp

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.Exp
import kr.or.dohands.dozon.exp.domain.ExpHistory
import kr.or.dohands.dozon.exp.domain.ExpRepository
import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.exp.service.ExpService
import kr.or.dohands.dozon.quest.service.JobQuestsService
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import kr.or.dohands.dozon.quest.service.SwordProjectService
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.domain.UserType
import kr.or.dohands.dozon.user.service.CareerService
import kr.or.dohands.dozon.user.service.LevelExpTypeService
import kr.or.dohands.dozon.user.service.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.IncorrectResultSizeDataAccessException
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.math.exp

@SpringBootTest
class ExpTest @Autowired constructor(
    private val userService: UserService,
    private val expHistoryService: ExpHistoryService,
    private val expService: ExpService,
    private val leaderQuestsService: LeaderQuestsService,
    private val entityManager: EntityManager,
    private val levelExpTypeService: LevelExpTypeService,
    private val jobQuestsService: JobQuestsService,
    private val swordProjectService: SwordProjectService
) {


    @Autowired
    private lateinit var careerService: CareerService

    @Test
    fun `올해 누적경험치를 조회한다`() {
        val number: Long = 2023010101
        val user: User = userService.findUserByNumber(number)
        val exp: Long? = expService.findtotalExpByNumber(user)

        Assertions.assertThat(exp).isNotNull
        Assertions.assertThat(exp).isNotEqualTo(0)
    }


    @Test
//    @Transactional
    fun `리더부여 퀘스트 경험치 부여 및 기록을 진행한다`() {
        val questId = 1L
        val questType = "리더부여 퀘스트"
        val quest = leaderQuestsService.findById(questId)

        // 이미 받은 경험치 기록인지 확인
        val check = expHistoryService.validate(questId, questType)
//        val date = LocalDateTime.now().monthOfYear.toString() + "-" + LocalDateTime.now().dayOfMonth.toString()
//        val date = Date().date

        // exp-history 에 기록
        if(check.equals(false)) {
            expHistoryService.write(
                LocalDateTime.now().year,
                quest.exp,
                quest.number.number,
                LocalDateTime.now(),
                quest.achievement,
                questType,
                quest.id
            )


            val exp = expService.findExpByNumber(quest.number)

            // exp 에 갱신
            val versus = exp.leaderQuests + quest.exp
            if(versus >= 2000){
                expService.updateLeaderQuestExpByNumber(2000, quest.number.number)
            }else{
                expService.updateLeaderQuestExpByNumber(quest.exp + exp.leaderQuests, quest.number.number)
            }

            expService.updateExpByNumber(quest.exp + exp.totalExp, quest.number.number)

            // user 에 갱신
            val user = userService.findUserByNumber(quest.number.number)
            userService.updateExp(user.exp + quest.exp, quest.number.number)

            entityManager.detach(user)
            val after = userService.findUserByNumber(quest.number.number)

            entityManager.detach(exp)
            val afterExp = expService.findExpByNumber(quest.number)

            Assertions.assertThat(afterExp.totalExp).isEqualTo(quest.exp + exp.totalExp)
            Assertions.assertThat(after.exp).isEqualTo(user.exp + quest.exp)
            if(versus >= 2000){
                Assertions.assertThat(afterExp.leaderQuests).isEqualTo(2000)
            }else{
                Assertions.assertThat(afterExp.leaderQuests).isEqualTo(exp.leaderQuests + quest.exp)
            }

            val level = levelExpTypeService.findByLevel(user.level.level)

            // user가 다음레벨까지 경험치를 도달하였는가?
            val nextLevel = levelExpTypeService.findNextLevelNowCheck(level.type, after.exp)
//        println("!!!"+nextLevel.get(0).exp + " " + nextLevel.get(1).exp)

            // 레벨업하기위한 경험치를 Level테이블에서 조회하고 그거 경험치랑 user겨ㅑㅇ험치랑 비교해서 맞다면 update level ㄱ
            if (after.exp >= nextLevel.get(0).exp) {
                println("!!!!")
                userService.updateLevel(nextLevel.get(0), user)
                entityManager.detach(after)
                val result = userService.findUserByNumber(quest.number.number)
                Assertions.assertThat(result.level.level).isEqualTo(nextLevel.get(0).level)
            }

            // exp 에서 올해 최대 경험치에 도달하였는가? , 특정 퀘스트의 올해 받을 수 있는 최대 경험치에 도달하였는가?
        }

    }

    // 직무별 퀘스트 : 1년 최대값 4000
    // 인사평가 : 1년 최대값 13000
    // 리더부여 퀘스트 : 1년 최대값 2000
    // 전사프로젝트 : 1년 최대값 없음

    @Test
    @Transactional
    fun `직무별 퀘스트 경험치 부여 및 기록을 진행한다`() {
        val questId = 1L
        val questType = "직무별 퀘스트"
        val quest = jobQuestsService.findById(questId)

        // 해당 센터에 해당하는 사람들을 전부 검색
        val career = careerService.findByName(quest.career)

        val users: List<User> = userService.findUsersByCareer(career)
        val userLength = users.size


        var productivityScore = ""
        if(quest.productivity >= BigDecimal(5.1)){
            productivityScore = "MAX"
        }else if(BigDecimal(5.1) > quest.productivity && quest.productivity >= BigDecimal(4.3)){
            productivityScore = "MEDIUM"
        }


        val check = expHistoryService.validate(questId, questType)

        for(i : Int in 0..userLength-1){
            val user = users.get(i)
            val userLevel = users.get(i).level

        if(check.equals(false)){
            expHistoryService.write(
                LocalDateTime.now().year,
                quest.exp,
                users.get(i).number,
                LocalDateTime.now(),
                productivityScore,
                questType,
                quest.id
            )

            // exp 갱신
            // jobquests , exp 둘다 갱신해야함ㄴ!!!
//            println("!!!" + users.get(i))

            if(user.userType != UserType.Admin){

                val exp = expService.findExpByUserNumber(user.number)
                val versus = exp.jobQuests + quest.exp
                println("!!!" + versus)
                if(versus >= 4000){
                    expService.updateJobQuestExpByNumber(4000, user.number)
                }else if(versus < 4000){
                    expService.updateJobQuestExpByNumber(exp.jobQuests+quest.exp, user.number)
                }
                expService.updateExpByNumber(exp.totalExp+quest.exp, user.number)

                val afterExp = expService.findExpByNumber(user)
                Assertions.assertThat(afterExp.totalExp).isEqualTo(quest.exp + exp.totalExp)
                if( versus > 4000){
                    Assertions.assertThat(4000).isEqualTo(afterExp.jobQuests)
                }else{
                    Assertions.assertThat(afterExp.jobQuests).isEqualTo(exp.jobQuests+quest.exp)
                }
            }

            // uesr 갱신
            entityManager.detach(user)
            val after = userService.findUserByNumber(user.number)

            val level = levelExpTypeService.findByLevel(userLevel.level)

            // user가 다음레벨까지 경험치를 도달하였는가?
            val nextLevel = levelExpTypeService.findNextLevelNowCheck(level.type, after.exp)
            // 레벨업하기 위한 경험치를 level테이블에서 조회하고 그거 경험치랑 user경험치랑 비교해서 맞다면 Update level ㄱ

            if(after.exp >= nextLevel.get(0).exp){
                println("!!!!")
                userService.updateLevel(nextLevel.get(0), user)
                entityManager.detach(after)
                val result = userService.findUserByNumber(user.number)
                Assertions.assertThat(result.level.level).isEqualTo(nextLevel.get(0).level) // 레벨이 올라갔다는 얘기
            }
            // exp에서 올해 최대 경험치에 도달하였는가? 특정 퀘스트의 올해 받을 수 있는 최대 경험치에 도달하였는가?

    }

    }

    }


    @Transactional
    @Test
    fun `전사 프로젝트 경험치 부여 및 기록을 진행한다`() {
        val id = 1L
        val questType = "전사 프로젝트"
        val quest = swordProjectService.findById(id)

        // exp history
        val check = expHistoryService.validate(id, questType)

        if(check == null){
            expHistoryService.write(
                LocalDateTime.now().year,
                quest.exp,
                quest.number.number,
                LocalDateTime.now(),
                "",
                questType,
                id
            )

            val exp = expService.findExpByNumber(quest.number)

            // exp

            expService.updateExpByNumber(quest.exp+ exp.totalExp, quest.number.number)
            expService.updateSwordProjectExpByNumber(quest.exp+exp.swordProject, quest.number.number)

            // user
            val user = userService.findUserByNumber(quest.number.number)
            userService.updateExp(user.exp+quest.exp, quest.number.number)

            // user 레벨업 판별
            entityManager.detach(user)
            val after = userService.findUserByNumber(quest.number.number)

            //
            entityManager.detach(exp)
            val afterExp = expService.findExpByNumber(quest.number)

            Assertions.assertThat(afterExp.totalExp).isEqualTo(quest.exp + exp.totalExp)
            Assertions.assertThat(after.exp).isEqualTo(user.exp+quest.exp)
            Assertions.assertThat(afterExp.swordProject).isEqualTo(exp.swordProject+quest.exp)

            val level = levelExpTypeService.findByLevel(user.level.level)

            // user가 다음레벨까지 경험치를 도달하였는가?
            val nextLevel = levelExpTypeService.findNextLevelNowCheck(level.type, after.exp)

            if(after.exp >= nextLevel.get(0).exp){
                println("!!!!")
                userService.updateLevel(nextLevel.get(0), user)
                entityManager.detach(after)
                val result = userService.findUserByNumber(quest.number.number)
                Assertions.assertThat(result.level.level).isEqualTo(nextLevel.get(0).level)
            }
        }

    }

}