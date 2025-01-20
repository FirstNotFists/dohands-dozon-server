package kr.or.dohands.dozon.quest.api

import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.quest.controller.data.Quests
import org.springframework.stereotype.Component
import java.time.format.TextStyle
import java.util.*

@Component
class JobQuestsHistory(
    private val expHistoryService: ExpHistoryService
): QuestHistoryProvider {
    override fun execute(number: Long): MutableList<Quests> {
        var list: MutableList<Quests> = mutableListOf()
        val quests = expHistoryService.findByQuestTypeAndNumberOrderByUpdateDate(number, "직무별 퀘스트")
        val length = quests.size
        if(quests.size > 0){
            for(i: Int in 0..length-1){
                val dayOfWeek = quests.get(i).updateDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                val month = quests.get(i).updateDate.month
                val day = quests.get(i).updateDate.dayOfMonth
                list.add(i, Quests.from(quests.get(i).questType, quests.get(i).exp,quests.get(i).grade , dayOfWeek, month.toString(), day.toString()))
            }
        }
        return list
    }
}