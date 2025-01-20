package kr.or.dohands.dozon.domain.sheet

import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.domain.ExpHistory
import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.exp.service.ExpService
import kr.or.dohands.dozon.quest.domain.HrEvaluationList
import kr.or.dohands.dozon.quest.domain.HrEvaluationListRepository
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.service.UserService
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.test.Test

@SpringBootTest
class HrEvaluationTest @Autowired constructor(
    private val googleSheetsService: GoogleSheetsService,
    private val expHistoryService: ExpHistoryService,
    private val expService: ExpService,
    private val userService: UserService,
    private val entityManager: EntityManager,
    private val hrEvaluationListRepository: HrEvaluationListRepository
) {

    private val format = SimpleDateFormat("yyyy-MM-dd")

    @Transactional
    @Test
    fun `인사평가 셀 데이터를 로드한다`() {
        val questType = "인사평가"
        val value = googleSheetsService.getValue(questType, "H10:K34") // 상반기 인사평가
        val valueLength = value.getValues().size
        var number : Long = 0
        var name = ""
        var grade = ""
        var exp : Long = 0
        var date : Date = Date()

//        val value2 = googleSheetsService.getValue("인사평가", "H10:K34") // 상반기 인사평가

        // 부여 경험치가 반영되었는지 확인 -> exp-history 에서 확인
        // 사번, 대상자, 인사평가 등급, 부여 경험치, 년도
//        println(LocalDate.now().year)
        for(i : Int in 0..valueLength-1) {
            // 사번, 이름, 등급, 부여 경험치
            println(value.getValues().get(i))
            number = value.getValues().get(i).get(0).toString().toLong()
            name = value.getValues().get(i).get(1).toString()
            grade = value.getValues().get(i).get(2).toString()
            exp = value.getValues().get(i).get(3).toString().toLong()
            date = format.parse("2024-06-30")

            val user = userService.findUserByNumber(number)
            Assertions.assertThat(user).isNotNull

            // 경험치 부여를 위한 준비
            val expHistory = ExpHistory(
                    0,
                    number,
                    questType,
                    LocalDateTime.now(),
                    2024,
                    exp,
                    grade,
                null
            )

            // 경험치 기록 추가
            // 경험치 부여
            // users
            userService.updateExp(number, user.exp + exp)
            // exp , 경험치 얼마, 어디 유형의
            expHistoryService.save(expHistory)
            // 상반기 인사평가 - firstEvaluation update or 하반기 인사평가 - secondEvaluation update
            expService.updateFirstEvaluation(number, exp)

            // 상반기 인사평가, 하반기 인사평가는 어떻게 구분하지?

            // 영속성 해제
            entityManager.detach(user)
            val detach = entityManager.contains(user)
            Assertions.assertThat(detach).isFalse()

            val resultNumber = userService.findUserByNumber(number)
            println(resultNumber.exp)
            Assertions.assertThat(resultNumber.exp).isEqualTo(exp + user.exp)

            val resultExp = expHistoryService.findExpHistoryByNumber(number)
            Assertions.assertThat(resultExp).isNotNull

            val resultEvaluation = expService.findByNumber(resultNumber)
            Assertions.assertThat(resultEvaluation).isNotNull
            Assertions.assertThat(resultEvaluation.firstEvaluation).isEqualTo(exp)

        }

        Assertions.assertThat(value).isNotNull()
        println(value.getValues())
    }

    @Test
    @Transactional
    fun `인사평가 셀 데이터를 연동 및 갱신한다`() {
        val questType = "인사평가"
        val beforeValues: List<HrEvaluationList> = hrEvaluationListRepository.findAll()
        val afterValues = googleSheetsService.getValue(questType, "H10:K34") // 상반기 인사평가


        val beforeLength = beforeValues.size
        val length = afterValues.getValues().size

        var number: Long
        var grade: String
        var exp: Long
        var date: String

        // 상반기 인사평가라... 판단하는건 if문에게 맡기고.
        // number , name, grade, exp
        if(beforeLength == 0){

            for(i: Int in 0..length-1){

                number = afterValues.getValues().get(i).get(0).toString().toLong()
                grade = afterValues.getValues().get(i).get(1).toString()
                exp = afterValues.getValues().get(i).get(3).toString().toLong()
                date = "06-30"

                hrEvaluationListRepository.save(
                    HrEvaluationList.toEntity(
                        number,
                        grade,
                        exp,
                        date
                    )
                )
            }

        }else if(beforeLength == length){
            for(i: Int in 0..length-1){
                number = afterValues.getValues().get(i).get(0).toString().toLong()
                grade = afterValues.getValues().get(i).get(1).toString()
                exp = afterValues.getValues().get(i).get(3).toString().toLong()

                if(number != beforeValues.get(i).number){
                    hrEvaluationListRepository.updateNumber(number, beforeValues.get(i).id)
                }

                if(grade != beforeValues.get(i).grade){
                    hrEvaluationListRepository.updateGrade(grade, beforeValues.get(i).id)
                }

                if(exp != beforeValues.get(i).exp){
                    hrEvaluationListRepository.updateExp(exp, beforeValues.get(i).id)
                }

            }
        }else if(beforeLength != length){
            hrEvaluationListRepository.deleteAll()
            // 다시 새로 생성
        }


        val result = hrEvaluationListRepository.findAll()
        println(result.get(0))

        Assertions.assertThat(result).isNotNull

    }

}