package kr.or.dohands.dozon.quest.match

import com.google.api.services.sheets.v4.model.ValueRange
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.quest.domain.SwordProject
import kr.or.dohands.dozon.quest.domain.SwordProjectRepository
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.stereotype.Service

@Service
class SwordProjectMatch(
    private val swordProjectRepository: SwordProjectRepository,
    private val googleSheetsService: GoogleSheetsService,
    private val userService: UserService
): SheetMatch {
    var date : String = ""
    var number :Long = 0
    var projectName : String = ""
    var exp : Long = 0
    var content: String = ""

    @Transactional
    override fun match() {
        sync()
    }

    private fun sync() {
        val questType = "전사 프로젝트"
        val value: ValueRange = googleSheetsService.getValue(questType, "B08:H12")
        val before : List<SwordProject> = swordProjectRepository.findAll()
        val length = value.getValues().size
        val beforeLength = before.size

        if(beforeLength == 0){
            migration(length,value)
        }else if(length == beforeLength){
            same(length, value, before)
        }else if(length != beforeLength){
            swordProjectRepository.deleteAll()
            migration(length,value)
            // length , beforeLength
        }

    }

    private fun migration(length: Int, value:ValueRange) {
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
    }

    private fun same(length: Int, value:ValueRange, before: List<SwordProject>) {
        try{
            for(i: Int in 0..length){
                date = value.getValues().get(i).get(0).toString()+"-"+value.getValues().get(i).get(1).toString()
                number = value.getValues().get(i).get(2).toString().toLong()
                val user = userService.findUserByNumber(number)
                projectName = value.getValues().get(i).get(4).toString()
                exp = value.getValues().get(i).get(5).toString().toLong()
                content = value.getValues().get(i).get(6).toString()
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
        }catch(exception: IndexOutOfBoundsException){
        }
    }




}