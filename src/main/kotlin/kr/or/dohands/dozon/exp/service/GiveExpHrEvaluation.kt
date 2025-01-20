package kr.or.dohands.dozon.exp.service

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.ExpHistory
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.time.LocalDateTime

@Component
class GiveExpHrEvaluation(
    private val googleSheetsService: GoogleSheetsService,
    private val userService: UserService,
    private val expService: ExpService,
    private val expHistoryService: ExpHistoryService
): GiveExpHandler {

    private var number: Long = 0
    private var name = ""
    private var grade = ""
    private var exp: Long = 0
    private var date: LocalDateTime = LocalDateTime.now()
    private val format = SimpleDateFormat("yyyy-MM-dd")

    private var centerName: String = ""
    private var group: Long = 0
    private var type: String = ""
    private var expOne: Long = 0
    private var weekProductivity: BigDecimal = BigDecimal(0.0)
    private var week: Long? = null
    private var month: Long? = null


    override fun give(questId: Long) {
        exp()
    }

    @Transactional
    fun exp() {
        evaluation("상반기 인사평가", "2024-06-30")
        evaluation("하반기 인사평가", "2024-12-31")
    }


    @Transactional
    fun evaluation(questType: String, dateRange: String): Unit {
        var cellRange = ""

        if (questType == "상반기 인사평가") {
            cellRange = "B10:F34"
        } else if (questType == "하반기 인사평가") {
            cellRange = "H10:K34"
        }

        val cellType = "인사평가"
        val value = googleSheetsService.getValue(cellType, cellRange) // 상반기 인사평가
        val valueLength = value.getValues().size


        for (i: Int in 0..valueLength - 1) {
            // 사번, 이름, 등급, 부여 경험치
            number = value.getValues().get(i).get(0).toString().toLong()
            name = value.getValues().get(i).get(1).toString()
            grade = value.getValues().get(i).get(2).toString()
            exp = value.getValues().get(i).get(3).toString().toLong()
            date = LocalDateTime.now()

            val user = userService.findUserByNumber(number)

            // 경험치 부여를 위한 준비
            val expHistory = ExpHistory(
                0,
                number,
                questType,
                date,
                2024,
                exp,
                grade,
                null
            )

            userService.updateExp(number, user.exp + exp)
            expHistoryService.save(expHistory)
            expService.updateFirstEvaluation(number, exp)
        }
    }

}