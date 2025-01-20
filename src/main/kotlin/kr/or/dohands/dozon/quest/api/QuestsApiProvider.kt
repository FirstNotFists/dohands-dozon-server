package kr.or.dohands.dozon.quest.api

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.quest.controller.data.*
import kr.or.dohands.dozon.quest.domain.LeaderQuests
import kr.or.dohands.dozon.quest.service.JobQuestsService
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import kr.or.dohands.dozon.quest.service.SwordProjectService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import org.springframework.web.servlet.resource.ResourceUrlProviderExposingInterceptor
import java.time.format.TextStyle
import java.util.*

@Component
class QuestsApiProvider(
    private val leaderQuestsService: LeaderQuestsService,
    private val jobQuestsService: JobQuestsService,
    private val userService: UserService,
    private val expHistoryService: ExpHistoryService,
    private val jobQuestCount: JobQuestCount,
    private val leaderQuestCount: LeaderQuestCount,
    private val swordProjectCount: SwordProjectCount
) {


    @Transactional
    fun quests(number: Long): QuestsResponse {
        val user = userService.findUserByNumber(number)

        val result = find(number)
        return QuestsResponse.from(result)

        // questType : String,
        // exp: Long,
        // dayday: String (요일),
        // month : String,
        // day : String (일 21)

    }


    fun find(number: Long):MutableList<Quests> {
        var list: MutableList<Quests> = mutableListOf()
        val quests = expHistoryService.findByNumberOrderByUpdateDate(number)
        val length = quests.size

            for(i: Int in 0..length-1){
                val dayOfWeek = quests.get(i).updateDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                val month = quests.get(i).updateDate.month
                val day = quests.get(i).updateDate.dayOfMonth
                list.add(i, Quests.from(quests.get(i).questType, quests.get(i).exp,quests.get(i).grade , dayOfWeek, month.toString(), day.toString()))
            }
        return list
    }


    fun top3(number: Long): QuestCount.Response {

        val result1 = jobQuestCount.execute(number)
        val result2 = leaderQuestCount.execute(number)
        val result3 = swordProjectCount.execute(number)
        val list = listOf(result1, result2, result3)

        return QuestCount.Response(list)
    }


    @Transactional
    fun productivity(number: Long): ProductGraph.List {
        val user = userService.findUserByNumber(number)
        val quests = jobQuestsService.findByCareer(user.career.name)
        val length = quests.size
        val list : MutableList<ProductGraph> = mutableListOf()

        for(i : Int in 0..length-1){
            val value = quests.get(i).productivity
            val week = quests.get(i).week

            list.add(ProductGraph.of(week,value))
        }

        return ProductGraph.List.from(list)

    }

    @Transactional
    fun readyQuest(number: Long):NotClearQuest {
        val history = expHistoryService.findExpHistoryByNumber(number)
        val length = history.size
        val list :MutableList<Long?> = mutableListOf()
        if(history.size == 0){
            list.add(1)
            return NotClearQuest(leaderQuestsService.findNotClear(list))
        }
        for(i : Int in 0..length-1){
            list.add( history.get(i).questId)
        }
        val result = leaderQuestsService.findNotClear(list)
        return NotClearQuest.from(result)
    }


}
