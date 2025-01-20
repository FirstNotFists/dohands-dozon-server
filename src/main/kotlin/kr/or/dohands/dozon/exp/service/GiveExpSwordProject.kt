package kr.or.dohands.dozon.exp.service

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.notification.service.NotificationService
import kr.or.dohands.dozon.quest.service.SwordProjectService
import kr.or.dohands.dozon.user.domain.LevelExpType
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.service.LevelExpTypeService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class GiveExpSwordProject @Autowired constructor(
    private val swordProjectService: SwordProjectService,
    private val expHistoryService: ExpHistoryService,
    private val expService: ExpService,
    private val userService: UserService,
    private val entityManager: EntityManager,
    private val levelExpTypeService: LevelExpTypeService,
    private val notificationService: NotificationService

):GiveExpHandler {

    override fun give(id: Long) {
        val questType = "전사 프로젝트"
        val quest = swordProjectService.findById(id)

        // exp history
        val check = expHistoryService.validate(id, questType)

        if(!check){
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
            entityManager.detach(exp)
            val afterExp = expService.findExpByNumber(quest.number)


            // user가 다음레벨까지 경험치를 도달하였는가?
//            val level = levelExpTypeService.findByLevel(user.level.level)
//            val nextLevel = levelExpTypeService.findNextLevelNowCheck(level.type, after.exp)
//            nextLevelCheck(after.exp, nextLevel.get(0), after)

            val result = userService.findUserByNumber(user.number)


            notificationService.swordProjectExp(user.id)
        }
    }

    fun levelUp(){
        val user = userService.findUsers()
        val length = user.size
        for(i :Int in 0..length-1){
            val nextLevel = levelExpTypeService.findNextLevelNowCheck(user.get(i).level.type, user.get(i).exp)
            nextLevelCheck(nextLevel.get(0), user.get(i))
            notificationService.levelUp(user.get(i).id)
        }


    }


    @Transactional
    fun nextLevelCheck(next: LevelExpType, user: User){
//        if (my >= next.exp) {
            println("!!!!" + next.level + " " + next.level)
            userService.updateLevel(next, user)
//        }
    }


}