package kr.or.dohands.dozon.service.sheet

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.ExpHistory
import kr.or.dohands.dozon.exp.service.GiveExpHrEvaluation
import kr.or.dohands.dozon.quest.match.HrEvaluationMatch
import kr.or.dohands.dozon.quest.service.HrEvaluationListService
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
class HrEvaluationMatchTest @Autowired constructor(
    private val hrEvaluationMatch: HrEvaluationMatch,
    private val giveExpHrEvaluation: GiveExpHrEvaluation,
    private val hrEvaluationListService: HrEvaluationListService
) {


    @Test
    fun `구글 시트 데이터를 연동한다`() {
        hrEvaluationMatch.match()
    }

    @Test
    fun `인사평가들을 로드하여 경험치를 일괄 지급한다`() {
        val list = hrEvaluationListService.findAll()
        val length = list.size

        for(i : Int in 0..<length){
            giveExpHrEvaluation.give(list.get(i).id)
        }
    }


}