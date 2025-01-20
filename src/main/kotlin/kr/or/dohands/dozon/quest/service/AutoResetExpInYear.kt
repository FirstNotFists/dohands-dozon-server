package kr.or.dohands.dozon.quest.service

import kr.or.dohands.dozon.exp.domain.ExpHistoryYear
import kr.or.dohands.dozon.exp.domain.ExpHistoryYearService
import kr.or.dohands.dozon.exp.service.ExpService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AutoResetExpInYear(
    private val expService: ExpService,
    private val expHistoryYearService: ExpHistoryYearService
) {

    fun reset() {
        calc()
    }

    private fun calc() {
        val list = expService.findAll()
        val length = list.size


        for(i : Int in 0..<length){
            val number = list.get(i).number.number
                expHistoryYearService.save(
                ExpHistoryYear(
                    0,
                    list.get(i),
                    list.get(i).year.toInt(),
                    list.get(i).totalExp
                )
            )
            // 초기화
            expService.updateFirstEvaluation(number, 0)
            expService.updateJobQuestExpByNumber(0, number)
            expService.updateLeaderQuestExpByNumber(0, number)
            expService.updateSwordProjectExpByNumber(0, number)
            expService.updateSecondEvaluation(0, number)
            expService.updateYear(LocalDateTime.now().year.toLong(), number)

        }
        // update 0으로 전부 만들기
        // delet는 하지말기
    }


}