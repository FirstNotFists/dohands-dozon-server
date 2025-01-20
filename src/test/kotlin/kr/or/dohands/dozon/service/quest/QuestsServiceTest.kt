package kr.or.dohands.dozon.service.quest

import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.notification.request.PushNotification
import kr.or.dohands.dozon.quest.service.JobQuestsService
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import kr.or.dohands.dozon.quest.service.SwordProjectService
import kr.or.dohands.dozon.user.service.CareerService
import kr.or.dohands.dozon.user.service.UserService
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.print.PrintService
import kotlin.math.exp
import kotlin.test.Test

@SpringBootTest
class QuestsServiceTest @Autowired constructor(
    private val jobQuestsService: JobQuestsService,
    private val userService: UserService,
    private val expHistoryService: ExpHistoryService,
    private val leaderQuestsService: LeaderQuestsService,
    private val swordProjectService: SwordProjectService
) {

    @Test
    fun `직무별 퀘스트 달성현황을 조회한다`() {
        val number = 2023010101L // minsukim
        val user = userService.findUserByNumber(number)
//        val careerParameter = user.career
        val career = user.career
//        val career = careerService.findByName(careerParameter)

        val result = jobQuestsService.findByCareer(career.name)

        Assertions.assertThat(result).isNotNull
        val questLength = result.size
        val list : HashMap<String, Any> = HashMap()

        // 직무별 퀘스트 , questId들로 exp-history 조회
        for(i : Int in 0..questLength-1){
            val questId = result.get(i).id
            val questType = "직무별 퀘스트"

            val result = expHistoryService.findByQuestIdAndQuestType(questId, questType)
            println(result)
            if(result.size > 0){
                list.put(i.toString(), result)
            }
        }
    }

    @Test
    fun `리더부여 퀘스트 달성현황을 조회한다`() {
        val number = 2023010101L
        val user = userService.findUserByNumber(number)

        val result = leaderQuestsService.findByUser(user)
        val questLength = result.size

        val list : HashMap<String, Any> = HashMap()

        Assertions.assertThat(result).isNotNull

        for(i : Int in 0..< questLength){
            val questId = result.get(i).id
            val questType = "리더부여 퀘스트"

            val result = expHistoryService.findByQuestIdAndQuestType(questId, questType)
            println(result)
            if(result != null){
                list.put(i.toString(), result)
            }
        }

        println(list)
    }

    @Test
    fun `전사 프로젝트 달성현황을 조회한다`() {
        val number = 2023010101L
        val user = userService.findUserByNumber(number)

        val result = swordProjectService.findByNumber(user)
        val questLength = result.size

        val list : HashMap<String, Any> = HashMap()

        Assertions.assertThat(result).isNotNull

        for(i : Int in 0..questLength-1){
            val questId = result.get(i).id
            val questType = "전사프로젝트"

            val result = expHistoryService.findByQuestIdAndQuestType(questId, questType)
            println(result)

            if(result != null){
                list.put(i.toString(), result)
            }
        }

        println(list)
    }

}