package kr.or.dohands.dozon.exp.service

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.Exp
import kr.or.dohands.dozon.notification.service.NotificationService
import kr.or.dohands.dozon.quest.domain.LeaderQuests
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import kr.or.dohands.dozon.user.domain.LevelExpType
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.service.LevelExpTypeService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class GiveExpLeaderQuest @Autowired constructor(
    private val leaderQuestsService: LeaderQuestsService,
    private val expHistoryService: ExpHistoryService,
    private val expService: ExpService,
    private val entityManager: EntityManager,
    private val userService: UserService,
    private val levelExpTypeService: LevelExpTypeService,
    private val notificationService: NotificationService

): GiveExpHandler {
    private var questType : String = "리더부여 퀘스트"
    private var productivityScore = ""

    @Transactional
    fun validateAndExist(quest: LeaderQuests) {
        expHistoryService.write(
            LocalDateTime.now().year,
            quest.exp,
            quest.number.number,
            LocalDateTime.now(),
            quest.achievement,
            questType,
            quest.id
        )
    }

    @Transactional
    fun giveExpToExpTable(totalExp: Long, user: User) {
        expService.updateExpByNumber(totalExp, user.number)
    }

    @Transactional
    fun maxExpCheck(versus: Long, exp: Exp, quest: LeaderQuests){
        if(versus >= 2000){
            expService.updateLeaderQuestExpByNumber(2000, quest.number.number)
        }else{
            expService.updateLeaderQuestExpByNumber(quest.exp + exp.leaderQuests, quest.number.number)
        }
    }

    @Transactional
    fun userExpUpdate(user: User , quest: LeaderQuests) {
        userService.updateExp(user.exp + quest.exp, quest.number.number)

    }

    @Transactional
    override fun give(questId: Long) :Unit{
        val quest = leaderQuestsService.findById(questId)
        // 이미 받은 경험치 기록인지 확인
        val check = expHistoryService.validate(questId, questType)
        // exp-history 에 기록
        if(!check){
            validateAndExist(quest)
        }

            val exp = expService.findExpByNumber(quest.number)

            // exp 에 갱신

            if(!check){
            val versus = exp.leaderQuests + quest.exp
            maxExpCheck(versus, exp, quest)
            giveExpToExpTable(quest.exp+exp.totalExp, quest.number)

            // user 에 갱신
            val user = userService.findUserByNumber(quest.number.number)
            userExpUpdate(user, quest)

            entityManager.detach(user)
            val after = userService.findUserByNumber(quest.number.number)

            entityManager.detach(exp)
            val afterExp = expService.findExpByNumber(quest.number)

            if(afterExp.totalExp == exp.totalExp+ quest.exp){
                notificationService.leaderQuestExp(user.id)
            }

            // user가 다음레벨까지 경험치를 도달하였는가?
            val level = levelExpTypeService.findByLevel(user.level.level)
            val nextLevel = levelExpTypeService.findNextLevelNowCheck(level.type, after.exp)

            // 레벨업하기위한 경험치를 Level테이블에서 조회하고 그거 경험치랑 user겨ㅑㅇ험치랑 비교해서 맞다면 update level

            val result = userService.findUserByNumber(user.number)
//                nextLevelCheck(after.exp, nextLevel.get(0), after)

//                if(user.level.level != result.level.level){
//                    notificationService.levelUp(user.id)
//                }
            }
            // exp 에서 올해 최대 경험치에 도달하였는가? , 특정 퀘스트의 올해 받을 수 있는 최대 경험치에 도달하였는가?
        }


//    @Transactional
//    fun nextLevelCheck(my: Long, next: LevelExpType, user:User){
//        if (my >= next.exp) {
//            println("!!!!" + my + " " + next.level)
//            userService.updateLevel(next, user)
//        }
//    }

}