package kr.or.dohands.dozon.quest.api

import kr.or.dohands.dozon.exp.controller.data.QuestWithHistoryInQuest
import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.quest.controller.data.Quests
import org.springframework.stereotype.Component
import java.time.format.TextStyle
import java.util.*

@Component
class SwordProjectHistory(
    private val expHistoryService: ExpHistoryService
)  {
    fun execute(number: Long): MutableList<QuestWithHistoryInQuest> {
        var list: MutableList<QuestWithHistoryInQuest> = mutableListOf()
        val quests = expHistoryService.findExpHistoryWithSwordProjects("전사 프로젝트", number)
        val length = quests.size
        if(quests.size > 0){
            for(i: Int in 0..length-1){
                println(quests.get(i).get(0))
                println(quests.get(i).get(1))
                println(quests.get(i).get(2))
                println(quests.get(i).get(3))
                println(quests.get(i).get(4))

                val exp = quests.get(i).get(0).toString().toLong()
                val content = quests.get(i).get(1).toString()
                val projectName = quests.get(i).get(2).toString()
                val date = quests.get(i).get(3).toString()
                if(i % 2 == 0){

                }
                val grade =quests.get(i).get(4).toString()

                list.add(
                    QuestWithHistoryInQuest.of(
                        grade,
                        date,
                        projectName,
                        content,
                        exp
                    )
                )
            }
        }
        return list
    }
}