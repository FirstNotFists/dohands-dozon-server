package kr.or.dohands.dozon.quest.match

import com.google.api.services.sheets.v4.model.ValueRange
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.quest.domain.JobQuests
import kr.or.dohands.dozon.quest.domain.JobQuestsRepository
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class JobQuestMatch(
    private val googleSheetsService: GoogleSheetsService,
    private val jobQuestsRepository: JobQuestsRepository
): SheetMatch {

    private var centerName : String = ""
    private var group: Long =0
    private var type: String =""
    private var expOne: Long =0
    private var weekProductivity: BigDecimal = BigDecimal(0.0)
    private var week : Long? = null
    private var month : Long? = null


    @Transactional
    override fun match() {
        val quests: List<JobQuests> = jobQuestsRepository.findAll()
        val productivity: ValueRange = googleSheetsService.getValue("직무별 퀘스트", "H14:I65") // 주차별 생산성
        val center: ValueRange = googleSheetsService.getValue("직무별 퀘스트", "F11:H11") // 센터
        val exp: ValueRange = googleSheetsService.getValue("직무별 퀘스트", "B14:C65") // 주차별 부여 경험치

        val centerLength: Int = center.getValues().size
        val expLength: Int = exp.getValues().size
        val productivityLength: Int = productivity.getValues().size

        centerName = center.getValues().get(0).get(0).toString() // 센터이름
        type = center.getValues().get(0).get(2).toString() // 주기
        group = center.getValues().get(0).get(1).toString().toLong() // 직무그룹

        if(quests.size == 0){
            migration(expLength, exp, productivity)
        }else if(expLength == productivityLength) {
            same(expLength, exp, productivity, quests, centerName, type)
        }else if(quests.size < expLength) {
            biggerThanSheet(exp, productivity)
        }


    }

    private fun biggerThanSheet(exp: ValueRange, productivity: ValueRange,) {
        if(type == "월"){
            month = exp.getValues().get(0).get(0).toString().toLong()
            weekProductivity = productivity.getValues().get(0).get(1).toString().toBigDecimal() // 생산성
            expOne = exp.getValues().get(0).get(1).toString().toLong() // 부여 경험치

            val jobQuests = JobQuests.toEntity(
                expOne,
                null,
                month,
                centerName,
                group,
                "",
                weekProductivity
            )

            jobQuestsRepository.save(jobQuests)
        }else if(type == "주"){
            week = exp.getValues().get(0).get(0).toString().toLong()
            weekProductivity = productivity.getValues().get(0).get(1).toString().toBigDecimal() // 생산성
            expOne = exp.getValues().get(0).get(1).toString().toLong() // 부여 경험치

            val jobQuests = JobQuests.toEntity(
                expOne,
                week,
                null,
                centerName,
                group,
                "",
                weekProductivity
            )

            jobQuestsRepository.save(jobQuests)
        }
    }

    private fun same(expLength: Int, exp: ValueRange, productivity: ValueRange, quests: List<JobQuests>, center: String, type:String) {
        for (i: Int in 0..expLength-1) {

            if(type == "월"){
                month = exp.getValues().get(i).get(0).toString().toLong()
                week = null

            } else if(type == "주"){
                month = null
                week = exp.getValues().get(i).get(0).toString().toLong()
            }

            weekProductivity = productivity.getValues().get(i).get(1).toString().toBigDecimal() // 생산성
            expOne = exp.getValues().get(i).get(1).toString().toLong() // 부여 경험치

            if(expOne != quests.get(i).exp) {
                jobQuestsRepository.updateExp(expOne, quests.get(i).id)
            }

            if(month != quests.get(i).month){
                jobQuestsRepository.updateMonth(month, quests.get(i).id)
            }

            if(centerName != quests.get(i).career) {
                jobQuestsRepository.updateCareer(center.toString(), quests.get(i).id)
            }

            if(group != quests.get(i).part) {
                jobQuestsRepository.updatePart(group, quests.get(i).id)
            }

            if(weekProductivity.toString().toBigDecimal() != quests.get(i).productivity.toString().toBigDecimal()) {
                jobQuestsRepository.updateProductivity(weekProductivity, quests.get(i).id)
            }

            if(week != quests.get(i).week){
                jobQuestsRepository.updateWeek(week, quests.get(i).id)
            }

        }
    }

    private fun migration(expLength: Int, exp:ValueRange, productivity: ValueRange) {
        for(i : Int in 0..expLength-1) {
            if(type == "주"){
                week = exp.getValues().get(i).get(0).toString().toLong()
                weekProductivity = productivity.getValues().get(i).get(1).toString().toBigDecimal() // 생산성
                expOne = exp.getValues().get(i).get(1).toString().toLong() // 부여 경험치S

                println(type)

                val jobQuests = JobQuests.toEntity(
                    expOne,
                    week,
                    null,
                    centerName,
                    group,
                    "",
                    weekProductivity
                )

                jobQuestsRepository.save(jobQuests)

            }else if(type == "월"){
                month = exp.getValues().get(i).get(0).toString().toLong()
                weekProductivity = productivity.getValues().get(i).get(1).toString().toBigDecimal() // 생산성
                expOne = exp.getValues().get(i).get(1).toString().toLong() // 부여 경험치

                val jobQuests = JobQuests.toEntity(
                    expOne,
                    null,
                    month,
                    centerName,
                    group,
                    "",
                    weekProductivity
                )

                jobQuestsRepository.save(jobQuests)

            }
        }
    }


}