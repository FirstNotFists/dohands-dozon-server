package kr.or.dohands.dozon.domain.sheet

import com.google.api.services.sheets.v4.model.ValueRange
import io.kotest.matchers.be
import kr.or.dohands.dozon.quest.domain.SwordProject
import kr.or.dohands.dozon.quest.domain.SwordProjectRepository
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class SwordProjectTest @Autowired constructor(
    private val swordProjectRepository: SwordProjectRepository,
    private val googleSheetsService: GoogleSheetsService,
    private val userService: UserService
) {
    @Test
    fun `전사 프로젝트 시트를 연동한다`() {
        val value: ValueRange = googleSheetsService.getValue("전사 프로젝트", "B08:H12")
        println(value)
    }

    @Test
    fun `전사 프로젝트를 연동하여 동기화한다`() {
        val value: ValueRange = googleSheetsService.getValue("전사 프로젝트", "B08:H12")
        val before : List<SwordProject> = swordProjectRepository.findAll()
        var date : String = ""
        var number :Long = 2023010101
        var projectName : String = ""
        var exp : Long = 0
        var content: String = ""
        val length = value.getValues().size
        val beforeLength = before.size

        // 월  일  사번  대상자  전사 프로젝트명  부여 경험치  비고
        // date number_number projectName exp content

        if(beforeLength == 0){
            for(i:Int in 0..length-1) {

                println(value.getValues())

                date = value.getValues().get(i).get(0).toString()+value.getValues().get(i).get(1).toString()
                number = value.getValues().get(i).get(2).toString().toLong()

                val user = userService.findUserByNumber(number)

                projectName = value.getValues().get(i).get(4).toString()
                exp = value.getValues().get(i).get(5).toString().toLong()
                content = value.getValues().get(i).get(6).toString()

                swordProjectRepository.save(
                    SwordProject.toEntity(
                        user,
                        date,
                        projectName,
                        exp,
                        content
                    )
                )
            }
        }else if(length == beforeLength){
            for(i: Int in 0..length){

                date = value.getValues().get(i).get(0).toString()+"-"+value.getValues().get(i).get(1).toString()
                number = value.getValues().get(i).get(2).toString().toLong()

                val user = userService.findUserByNumber(number)

                projectName = value.getValues().get(i).get(4).toString()
                exp = value.getValues().get(i).get(5).toString().toLong()
//                content = value.getValues().get(i).get(5).toString()

            if(date != before.get(i).date){
                swordProjectRepository.updateDate(date, before.get(i).id)
            }
//            if(number != before.get(i).number.number){
//                swordProjectRepository.updateUser(user, before.get(i).id)
//            }
            if(projectName != before.get(i).projectName){
                swordProjectRepository.updateProjectName(projectName, before.get(i).id)
            }
            if(exp != before.get(i).exp){
                swordProjectRepository.updateExp(exp, before.get(i).id)
            }
            if(content != before.get(i).content){
                swordProjectRepository.updateContent(content, before.get(i).id)
            }

        }

        }else if(length != beforeLength){
            swordProjectRepository.deleteAll()
            // length , beforeLength
        }

        println(value.getValues())

    }

}