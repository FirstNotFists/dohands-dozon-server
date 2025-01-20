package kr.or.dohands.dozon.quest.match

import com.google.api.services.sheets.v4.model.ValueRange
import jakarta.persistence.EntityManager
import kr.or.dohands.dozon.quest.domain.HrEvaluationList
import kr.or.dohands.dozon.quest.domain.HrEvaluationListRepository
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat

@Service
class HrEvaluationMatch(
    private val googleSheetsService: GoogleSheetsService,
    private val hrEvaluationListRepository: HrEvaluationListRepository,
    private val entityManager: EntityManager
): SheetMatch {
    private var number : Long = 0
    private var name = ""
    private var grade = ""
    private var exp : Long = 0
    private var date : String = ""
    private val format = SimpleDateFormat("yyyy-MM-dd")

    override fun match() {
        sync("상반기 인사평가")
        sync("하반기 인사평가")
    }


    private fun sync(questType: String) {
        val beforeValues: List<HrEvaluationList> = hrEvaluationListRepository.findAll()
        var afterValues: ValueRange = ValueRange()
        var cellRange = ""

        if (questType == "상반기 인사평가") {
            cellRange = "B10:F34"
        } else if (questType == "하반기 인사평가") {
            cellRange = "H10:K34"
        }

        if(questType == "상반기 인사평가"){
            afterValues = googleSheetsService.getValue("인사평가",cellRange) // 상반기 인사평가
            date = "06-30"
        }else if(questType == "하반기 인사평가"){
            afterValues = googleSheetsService.getValue("인사평가", cellRange) // 상반기 인사평가
            date = "12-31"
        }


        val beforeLength = beforeValues.size
        val length = afterValues.getValues().size

//        var number: Long
//        var grade: String
//        var exp: Long
//        var date: String

        // 상반기 인사평가라... 판단하는건 if문에게 맡기고.
        // number , name, grade, exp
//        if(beforeLength == 0){
        migration(length, afterValues)
//        }else if(beforeLength*2 == length){
//            same(length, afterValues, beforeValues)
//        }else if(beforeLength != length){
//            hrEvaluationListRepository.deleteAll()
//            // 다시 새로 생성
//        }


    }

    fun migration(length: Int, afterValues: ValueRange) {
        for(i: Int in 0..length-1){
            println(afterValues.getValues())

            number = afterValues.getValues().get(i).get(0).toString().toLong()
            grade = afterValues.getValues().get(i).get(2).toString()
            exp = afterValues.getValues().get(i).get(3).toString().toLong()

            val entity = HrEvaluationList.toEntity(
                number,
                grade,
                exp,
                date,
            )

//            entityManager.detach(entity)
            hrEvaluationListRepository.save(entity)
        }
    }

    fun same(length: Int, afterValues: ValueRange, beforeValues: List<HrEvaluationList>) {
        for(i: Int in 0..length-1){
            number = afterValues.getValues().get(i).get(0).toString().toLong()
            grade = afterValues.getValues().get(i).get(2).toString()
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

            if(date != beforeValues.get(i).date){
                hrEvaluationListRepository.updateDate(date, beforeValues.get(i).id)
            }
        }
    }

}