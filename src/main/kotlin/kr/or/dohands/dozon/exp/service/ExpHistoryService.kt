package kr.or.dohands.dozon.exp.service

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.ExpHistory
import kr.or.dohands.dozon.exp.domain.ExpHistoryRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

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



        // 있다면 부여 경험치가 같은지 매칭
        // 없다면 기록을 추가하고 부여 경험치 부여
        // 어떤 주기로 돌아가도록 할까 .. ㅠㅠ
        // 기본은 하루에 한번 , 일주일에 한번 , 한달에 한번 이런식으로 들어가는게 맞을 듯하다
        // 하루에 한번 돌아간다 치고의 clock을 활용한 테스트 코드를 작성해야할 듯하다.

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


}