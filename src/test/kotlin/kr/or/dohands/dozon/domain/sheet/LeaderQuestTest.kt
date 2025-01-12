package kr.or.dohands.dozon.domain.sheet

import com.google.api.services.sheets.v4.model.ValueRange
import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.Exp
import kr.or.dohands.dozon.exp.domain.ExpRepository
import kr.or.dohands.dozon.quest.domain.LeaderQuests
import kr.or.dohands.dozon.quest.domain.LeaderQuestsRepository
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.domain.UserRepository
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class LeaderQuestTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val leaderQuestsRepository: LeaderQuestsRepository,
    private val googleSheetsService: GoogleSheetsService,
    private val entityManager: EntityManager,
    private val expRepository: ExpRepository
) {


    @Transactional
    @Test
    fun `추가된 리더부여 퀘스트를 디비로 반영한다`() {
        // 파일 변경을 감지
        // 파일 리딩
        // 추가되거나 변경된 데이터를 감지

        //  월, 사번, 대상자, 리더 부여 퀘스트명, 달성내용, 부여 경험치, 비고
        val value: ValueRange = googleSheetsService.getValue("리더부여 퀘스트", "B10:H50")
        val length = value.getValues().size
        var month: Int
        var user: User
        var username: String
        var questName: String
        var achievement: String
        var exp: Long
        var content: String

        for (i: Int in 0..length - 1) {
            println(value.getValues())

            month = value.getValues().get(i).get(0).toString().toInt()
            user = userRepository.findUserByNumber(value.getValues().get(i).get(1).toString().toLong())
            username = value.getValues().get(i).get(2).toString()
            questName = value.getValues().get(i).get(3).toString()
            achievement = value.getValues().get(i).get(4).toString()
            exp = value.getValues().get(i).get(5).toString().toLong()
            content = value.getValues().get(i).get(6).toString()

            val quest: LeaderQuests = leaderQuestsRepository.save(
                LeaderQuests.toEntity(
                    month,
                    user,
                    questName,
                    achievement,
                    exp,
                    content
                )
            )
        }
    }

    @Test
    fun `리더부여 퀘스트가 업데이트 되었는지 확인 후 추가하거나 제거한다`() {
        // 리더부여 퀘스트를 새로 추가해서 작성한 스프레드시트
        val value: ValueRange = googleSheetsService.getValue("리더부여 퀘스트", "B10:H999")
        Assertions.assertThat(value).isNotNull

        val length = value.getValues().size
        var month: Int
        var user: User
        var username: String
        var questName: String
        var achievement: String
        var exp: Long
        var content: String

        val quests: List<LeaderQuests> = leaderQuestsRepository.findAll()

        val before: Int = quests.size // db
        val after: Int = value.getValues().size // sheet
        println(before)
        println(after)

        if(before == 0){ // 디비가 비어있으면 스프레드시트 주입

            for (i: Int in 0..length - 1) {
                var month: Int
                var user: User
                var username: String
                var questName: String
                var achievement: String
                var exp: Long
                var content: String

                month = value.getValues().get(i).get(0).toString().toInt()
                user = userRepository.findUserByNumber(value.getValues().get(i).get(1).toString().toLong())
                username = value.getValues().get(i).get(2).toString()
                questName = value.getValues().get(i).get(3).toString()
                achievement = value.getValues().get(i).get(4).toString()
                exp = value.getValues().get(i).get(5).toString().toLong()
                content = value.getValues().get(i).get(6).toString()

                val quest: LeaderQuests = leaderQuestsRepository.save(
                    LeaderQuests.toEntity(
                        month,
                        user,
                        questName,
                        achievement,
                        exp,
                        content
                    )
                )
            }

        } else if(after > before) { // 데이터가 늘어난 것으로 판단

            month = value.getValues().get(after-1).get(0).toString().toInt()
            user = userRepository.findUserByNumber(value.getValues().get(after-1).get(1).toString().toLong())
            username = value.getValues().get(after-1).get(2).toString()
            questName = value.getValues().get(after-1).get(3).toString()
            achievement = value.getValues().get(after-1).get(4).toString()
            exp = value.getValues().get(after-1).get(5).toString().toLong()
            content = value.getValues().get(after-1).get(6).toString()

            leaderQuestsRepository.save(
                    LeaderQuests.toEntity(
                        month,
                        user,
                        questName,
                        achievement,
                        exp,
                        content
                    )
            )
        }else if(after < before) { // 데이터가 줄어든 것으로 판단
            leaderQuestsRepository.deleteById(quests.get(before-1).id)
        }

    }

    @Test
    @Transactional
    fun `퀘스트가 달성되면서 경험치를 해당 유저에게 부여한다`() {
        val exp : Long = 100
        val number: Long = 2023010102
        val before: User = userRepository.findUserByNumber(number)

        entityManager.detach(before)

        val detached = entityManager.contains(before)
        Assertions.assertThat(detached).isFalse() // 비영속 상태 확인

        val result: Int = userRepository.updateExpByNumber(exp+ before.exp, number)
        val after: User = userRepository.findUserByNumber(number)

        //exp 테이블로 갱신
        val beforeExp : Exp = expRepository.findExpByNumber(before)
        entityManager.detach(beforeExp)

        val detachedExp = entityManager.contains(beforeExp)
        Assertions.assertThat(detachedExp).isFalse() // 비영속 상태 확인

        Assertions.assertThat(beforeExp).isNotNull
        expRepository.updateExpByNumber(beforeExp.totalExp + exp, number)

        val afterExp: Exp = expRepository.findExpByNumber(before)
        Assertions.assertThat(afterExp.totalExp).isEqualTo(beforeExp.totalExp+exp)
        Assertions.assertThat(after.exp).isEqualTo(exp+ before.exp)

    }

    @Test
    fun `변경사항에 대해서 상세히 매치하여 업데이트한다`() { // user만 변경안됨
        // 데이터 개수가 서로 같을 경우에 작동하도록
        val value: ValueRange = googleSheetsService.getValue("리더부여 퀘스트", "B10:H50")
        val quests: List<LeaderQuests> = leaderQuestsRepository.findAll()

        val length = value.getValues().size
        var month: Int
        var user: User
        var number: Long
        var username: String
        var questName: String
        var achievement: String
        var exp: Long
        var content: String

        println(value.getValues())

        for (i: Int in 0..length - 1) {

            month = value.getValues().get(i).get(0).toString().toInt()
            user = userRepository.findUserByNumber(value.getValues().get(i).get(1).toString().toLong())
            number = value.getValues().get(i).get(1).toString().toLong()
            username = value.getValues().get(i).get(2).toString()
            questName = value.getValues().get(i).get(3).toString()
            achievement = value.getValues().get(i).get(4).toString()
            exp = value.getValues().get(i).get(5).toString().toLong()
            content = value.getValues().get(i).get(6).toString()

            if(month != quests.get(i).month){
                leaderQuestsRepository.updateMonth(month, quests.get(i).id)
            }

            if(questName != quests.get(i).questName){
                leaderQuestsRepository.updatequestName(questName, quests.get(i).id)
            }

            if(achievement != quests.get(i).achievement){
                leaderQuestsRepository.updateAchievement(achievement, quests.get(i).id)
            }

            if(exp != quests.get(i).exp){
                leaderQuestsRepository.updateExp(exp, quests.get(i).id)
            }

            if(content != quests.get(i).content){
                leaderQuestsRepository.updateContent(content, quests.get(i).id)
            }

        }
    }


//    @Test
//    fun ``() {
//
//    }


}

