package kr.or.dohands.dozon.quest.api

import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.quest.controller.data.QuestCount
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component

@Component
class LeaderQuestCount(
    private val expHistoryService: ExpHistoryService,
    private val leaderQuestsService: LeaderQuestsService
): QuestCountProvider {
    override fun execute(number: Long): QuestCount {
        val questType = "리더부여 퀘스트"
        var clear =  0L
        try{
            clear = expHistoryService.findCountByQuestTypeAndNumberHighExp(questType, number)
        }catch(exception: EmptyResultDataAccessException){
            clear = 0L
        }
        val total = leaderQuestsService.findCountAll()
        val questName = "업무 개선"
        return QuestCount.of(questType, clear,total,questName)
    }
}