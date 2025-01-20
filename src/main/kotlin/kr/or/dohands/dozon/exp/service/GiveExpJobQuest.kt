package kr.or.dohands.dozon.exp.service

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.Exp
import kr.or.dohands.dozon.notification.service.NotificationService
import kr.or.dohands.dozon.quest.domain.JobQuests
import kr.or.dohands.dozon.quest.service.JobQuestsService
import kr.or.dohands.dozon.user.domain.LevelExpType
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.domain.UserType
import kr.or.dohands.dozon.user.service.CareerService
import kr.or.dohands.dozon.user.service.LevelExpTypeService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Component
class GiveExpJobQuest @Autowired constructor(
    private val jobQuestsService: JobQuestsService,
    private val careerService: CareerService,
    private val userService: UserService,
    private val expHistoryService: ExpHistoryService,
    private val expService: ExpService,
    private val entityManager: EntityManager,
    private val levelExpTypeService: LevelExpTypeService,
    private val notificationService: NotificationService
) :GiveExpHandler{

    private var questType : String = "직무별 퀘스트"
    private var productivityScore = ""

    @Transactional
    fun productivityCondition(quest: JobQuests){
        if (quest.productivity >= BigDecimal(5.1)) {
            productivityScore = "MAX"
        } else if (BigDecimal(5.1) > quest.productivity && quest.productivity >= BigDecimal(4.3)) {
            productivityScore = "MEDIUM"
        }
    }

    @Transactional
    fun validateAndExist(userLength: Int, quest:JobQuests, users: List<User>){
            for (i: Int in 0..userLength - 1) {
                expHistoryService.write(
                    LocalDateTime.now().year,
                    quest.exp,
                    users.get(i).number,
                    LocalDateTime.now(),
                    productivityScore,
                    questType,
                    quest.id
                )
        }
    }

    @Transactional
    fun giveExpToExpTable(totalExp: Long, user: User) {
        expService.updateExpByNumber(totalExp, user.number)
    }

    @Transactional
    fun maxExpCheck(versus: Long, number: Long, jobQuests:Long) {
        if (versus >= 4000) { // 최대값이 4000을 넘을려고 하면 4000으로 맞추기
            expService.updateJobQuestExpByNumber(4000, number)
        } else if (versus < 4000) {
            expService.updateJobQuestExpByNumber(versus, number)
        }
    }

    override fun give(questId: Long): Unit {
        val questType = "직무별 퀘스트"
        val quest = jobQuestsService.findById(questId)

        // 해당 센터에 해당하는 사람들을 전부 검색
        val career = careerService.findByName(quest.career)

        val users: List<User> = userService.findUsersByCareer(career)
        val userLength = users.size
//        var exp: Exp = Exp(0,0,0,0,0, users.get(0), 0,0,0)
//        var afterExp :Exp =  Exp(0,0,0,0,0,users.get(0), 0,0,0)


        productivityCondition(quest)

        val check = expHistoryService.validate(questId, questType)

        if(!check){
            validateAndExist(userLength, quest, users)
        }


        for (i: Int in 0..userLength - 1) {
            if(!check){

            val user = users.get(i)
            val userLevel = users.get(i).level

            // exp갱신 : jobquests , exp 둘다 갱신해야함ㄴ!!!
            if (user.userType != UserType.Admin) {

                val exp = expService.findExpByUserNumber(user.number)
                val versus = exp.jobQuests + quest.exp // jobQuest의 최대수치를 측정하기 위한

                maxExpCheck(versus, user.number, exp.jobQuests)

                giveExpToExpTable(exp.totalExp+quest.exp, user)

                entityManager.detach(exp)
                val afterExp = expService.findExpByNumber(user)

                userExpUpdate(user.exp + quest.exp, user.number)

                // uesr 갱신
                entityManager.detach(user)
                val after = userService.findUserByNumber(user.number)

                // user가 다음레벨까지 경험치를 도달하였는가?
                val level = levelExpTypeService.findByLevel(userLevel.level)
                val nextLevel = levelExpTypeService.findNextLevelNowCheck(level.type, after.exp)

                // 레벨업하기 위한 경험치를 level테이블에서 조회하고 그거 경험치랑 user경험치랑 비교해서 맞다면 Update level ㄱ
                // exp에서 올해 최대 경험치에 도달하였는가? 특정 퀘스트의 올해 받을 수 있는 최대 경험치에 도달하였는가?
//                nextLevelCheck(after.exp, nextLevel.get(0), user)

                if (afterExp.totalExp == exp.totalExp + quest.exp) { // exp테이블에
                    giveExpNotification(users.get(i).id)
                }

                val result = userService.findUserByNumber(user.number)

//                if(user.level.level != result.level.level){
//                    levelUpNotification(user.id)
//                }

            }

            }
        }

    }

    @Transactional
    fun giveExpNotification(id: String) {
        notificationService.jobQuestExp(id)

    }

    @Transactional
    fun userExpUpdate(exp: Long, number: Long) {
        userService.updateExp(exp, number)

    }

//    @Transactional
//    fun nextLevelCheck(my: Long, next: LevelExpType, user:User) {
//        println("!!!!"+ my+" " + next.exp)
//        if (my > next.exp || my == next.exp) {
//            userService.updateLevel(next, user)
//        }
//    }

    @Transactional
    fun levelUpNotification(id : String) {
        notificationService.levelUp(id)
    }
}

