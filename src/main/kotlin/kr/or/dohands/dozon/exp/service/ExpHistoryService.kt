package kr.or.dohands.dozon.exp.service

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.ExpHistory
import kr.or.dohands.dozon.exp.domain.ExpHistoryRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ExpHistoryService(
    private val expHistoryRepository: ExpHistoryRepository,
) {

    @Transactional
    fun findExpHistoryByNumber(number: Long): List<ExpHistory> {
        return expHistoryRepository.findExpHistoryByNumber(number)
    }

    fun save(expHistory: ExpHistory) {
        expHistoryRepository.save(expHistory)
    }

    fun write(
                year: Int,
                 exp: Long,
                 number: Long,
                 updateDate: LocalDateTime,
                 grade: String,
                 questType: String,
                questId : Long
    ): String? {

        val entity = ExpHistory.toEntity(number, questType, updateDate, year, exp, grade, questId)
        save(entity)

        return "기록 완료"
    }


    fun validate(questId : Long, questType : String) : Boolean {
        // true : 이미 있음
        // false : 없음
        val result= expHistoryRepository.findByQuestIdAndQuestType(questId, questType)
        if(result.size >0){
            return true
        }else{
            return false
        }
    }


    fun findByQuestIdAndQuestType(questId: Long, questType: String): List<ExpHistory> {
        return expHistoryRepository.findByQuestIdAndQuestType(questId, questType)
    }

    fun findByNumberOrderByUpdateDate(number: Long): List<ExpHistory> {
        return expHistoryRepository.findByNumberOrderByUpdateDate(number)
    }

    fun findByQuestTypeAndNumberOrderByUpdateDate(number: Long, questType: String): List<ExpHistory> {
        return expHistoryRepository.findByQuestTypeAndNumberOrderByUpdateDate(number, questType)
    }


    fun findCountByQuestTypeAndNumberHighExp(questType: String, number: Long): Long {
        return expHistoryRepository.findCountByQuestTypeAndNumberHighExp(questType, number)
    }

    fun findExpHistoryWithLeaderQuests(number: Long, questType: String): List<Array<Any>> {
        return expHistoryRepository.findExpHistoryWithLeaderQuests(questType, number)
    }

    fun findExpHistoryWithSwordProjects(questType: String, number: Long): List<Array<Any>> {
        return expHistoryRepository.findExpHistoryWithSwordProjects(questType, number)
    }


}