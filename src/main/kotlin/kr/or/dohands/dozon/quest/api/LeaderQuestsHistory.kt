package kr.or.dohands.dozon.quest.api

import kr.or.dohands.dozon.exp.controller.data.QuestWithHistoryInQuest
import kr.or.dohands.dozon.exp.service.ExpHistoryService
import org.springframework.stereotype.Component

@Component
class LeaderQuestsHistory(
    private val expHistoryService: ExpHistoryService
)  {

    fun execute(number: Long):MutableList<QuestWithHistoryInQuest> {
        val quests = expHistoryService.findExpHistoryWithLeaderQuests(number, "리더부여 퀘스트")
        val length = quests.size
        val list : MutableList<QuestWithHistoryInQuest> = mutableListOf()

        for(i : Int in 0..length-1){
            println(quests.get(i).get(0)) // year
            println(quests.get(i).get(1)) // exp
            println(quests.get(i).get(2)) //
            println(quests.get(i).get(3))
            println(quests.get(i).get(4))
            val exp = quests.get(i).get(0).toString().toLong()
            val content = quests.get(i).get(1).toString()
            val questName = quests.get(i).get(2).toString()
            val date = quests.get(i).get(3).toString()
            val grade = quests.get(i).get(4).toString()

            list.add(
            QuestWithHistoryInQuest.of(
                grade,
                date,
                questName,
                content,
                exp
            )
            )
        }
        return list
    }
}