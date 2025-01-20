package kr.or.dohands.dozon.quest.api

import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.quest.controller.data.QuestCount
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class JobQuestCount(
    private val expHistoryService: ExpHistoryService
):QuestCountProvider {
    override fun execute(number: Long): QuestCount {
        val questType = "직무별 퀘스트"
        var clear = 0L
        try{
            clear = expHistoryService.findCountByQuestTypeAndNumberHighExp(questType, number)
        }catch(exception: EmptyResultDataAccessException){
            clear = 0L
        }
        val total = 50L
        val questName = "주별 직무 과제"
        return QuestCount.of(questType,clear,total,questName)
    }
}