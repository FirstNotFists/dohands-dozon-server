package kr.or.dohands.dozon.domain.sheet

import com.google.api.services.sheets.v4.model.ValueRange
import kr.or.dohands.dozon.quest.domain.JobQuests
import kr.or.dohands.dozon.quest.domain.JobQuestsRepository
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import kotlin.test.Test

@SpringBootTest
class JobQuestTest @Autowired constructor(
    private val googleSheetsService: GoogleSheetsService,
    private val jobQuestsRepository: JobQuestsRepository
) {


    @Test
    fun `직무별 퀘스트 부여 경험치를 탐색 후 갱신한다` () {
        val value: ValueRange = googleSheetsService.getValue("직무별 퀘스트", "B14:C65")
        Assertions.assertThat(value).isNotNull

        println(value.getValues())

    }


//        exp : 부여 경험치
//        week : 주차
//        month : 월별
//        career : 센터이름
//        part : 직무그룹
    @Test
    fun `직무별 퀘스트, 해당 센터가 주간 부여 경험치를 로드 하여 데이터베이스를 갱신한다`() {

        val quests: List<JobQuests> = jobQuestsRepository.findAll()
        val productivity: ValueRange = googleSheetsService.getValue("직무별 퀘스트", "H14:I65") // 주차별 생산성
        val center: ValueRange = googleSheetsService.getValue("직무별 퀘스트", "F11:H11") // 센터
        val exp: ValueRange = googleSheetsService.getValue("직무별 퀘스트", "B14:C65") // 주차별 부여 경험치
        val centerLength: Int = center.getValues().size
        val expLength: Int = exp.getValues().size
        val productivityLength: Int = productivity.getValues().size
        var centerName : String = ""
        var group: Long =0
        var type: String =""
        var expOne: Long =0
        var weekProductivity: BigDecimal = BigDecimal(0.0)
        var week : Long? = null
        var month : Long? = null

        centerName = center.getValues().get(0).get(0).toString() // 센터이름
        type = center.getValues().get(0).get(2).toString() // 주기
        group = center.getValues().get(0).get(1).toString().toLong() // 직무그룹

        println(expLength)

    if(quests.size == 0){

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
        }else if(expLength == productivityLength) {
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

//                Assertions.assertThat(expOne).isEqualTo(quests.get(i).exp)
                if(expOne != quests.get(i).exp) {
                    jobQuestsRepository.updateExp(expOne, quests.get(i).id)
                }

//                Assertions.assertThat(month).isEqualTo(quests.get(i).month)
                if(month != quests.get(i).month){
                    jobQuestsRepository.updateMonth(month, quests.get(i).id)
                }

//                Assertions.assertThat(centerName).isEqualTo(quests.get(i).career)
                if(centerName != quests.get(i).career) {
                    jobQuestsRepository.updateCareer(center.toString(), quests.get(i).id)
                }

//                Assertions.assertThat(group).isEqualTo(quests.get(i).part)
                if(group != quests.get(i).part) {
                    jobQuestsRepository.updatePart(group, quests.get(i).id)
                }

//                println(quests.get(i).productivity)
//                println(weekProductivity)

//                Assertions.assertThat(weekProductivity.toString().toBigDecimal()).isEqualTo(quests.get(i).productivity)
                if(weekProductivity.toString().toBigDecimal() != quests.get(i).productivity.toString().toBigDecimal()) {
                    jobQuestsRepository.updateProductivity(weekProductivity, quests.get(i).id)
                }

//                Assertions.assertThat(week).isEqualTo(quests.get(i).week)
                if(week != quests.get(i).week){
                    jobQuestsRepository.updateWeek(week, quests.get(i).id)
                }

            }

        }else if(quests.size < expLength){
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

        Assertions.assertThat(center).isNotNull()
        Assertions.assertThat(exp).isNotNull()

    }

    // 새로운 데이터인지 판단 -> 월, 사번 두개를 가지고 매치 해보자.
    // 0번쨰 : 월
    // 1번째 : 사번

}