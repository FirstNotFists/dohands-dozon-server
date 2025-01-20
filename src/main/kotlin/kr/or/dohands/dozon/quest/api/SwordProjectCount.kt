package kr.or.dohands.dozon.quest.api

import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.quest.controller.data.QuestCount
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class SwordProjectCount(
    private val expHistoryService: ExpHistoryService
): QuestCountProvider {
    override fun execute(number: Long): QuestCount {
        val questType = "전사 프로젝트"
        var clear : Long = 0L
        try{
            clear = expHistoryService.findCountByQuestTypeAndNumberHighExp(questType, number)
        }catch(exception: EmptyResultDataAccessException){
            clear = 0L
        }
        val total = 0L
        return QuestCount.of(questType, clear, total, "전사 프로젝트 과제")
    }
}