package kr.or.dohands.dozon.quest.match

import com.google.api.services.sheets.v4.model.ValueRange
import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.service.GiveExpJobQuest
import kr.or.dohands.dozon.exp.service.GiveExpLeaderQuest
import kr.or.dohands.dozon.quest.domain.LeaderQuests
import kr.or.dohands.dozon.quest.domain.LeaderQuestsRepository
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class LeaderQuestMatch(
    private val leaderQuestsRepository: LeaderQuestsRepository,
    private val googleSheetsService: GoogleSheetsService,
    private val userService: UserService,
    private val leaderQuestsService: LeaderQuestsService,
    private val giveExpLeaderQuest: GiveExpLeaderQuest
) :SheetMatch {

    private var month : Int = 0
    private var username : String = ""
    private var questName : String = ""
    private var achievement : String = ""
    private var exp : Long = 0
    private var content : String = ""

    fun save(quest: LeaderQuests) {
        leaderQuestsRepository.save(quest)
    }

    fun deleteById(id: Long){
        leaderQuestsRepository.deleteById(id)
    }

    fun deleteAll() {
        leaderQuestsRepository.deleteAll()
    }


//    @Transactional
    override fun match() {
        val sheet: ValueRange = googleSheetsService.getValue("리더부여 퀘스트", "B10:H999")
        val quests: List<LeaderQuests> = leaderQuestsRepository.findAll()

        val sheetLength: Int = sheet.getValues().size
        val questLength: Int = quests.size


            if (questLength == 0) {
                migration(sheetLength, sheet)
            } else if (questLength == sheetLength) {
                same(sheetLength, quests, sheet)
            } else if (questLength != sheetLength) {
                different(
                    sheet.getValues(), sheetLength-1)
            }

    }

    private fun different(value: List<List<Any>>, length: Int) {
        month = value.get(length).get(0).toString().toInt()
        val user = userService.findUserByNumber(value.get(length).get(1).toString().toLong())
        username = value.get(length).get(2).toString()
        questName = value.get(length).get(3).toString()
        achievement = value.get(length).get(4).toString()
        exp = value.get(length).get(5).toString().toLong()
        content = value.get(length).get(6).toString()

        save(
            LeaderQuests(
                0,
                month,
                user,
                questName,
                achievement,
                exp,
                content
            )
        )

    }

    private fun migration(length: Int, value: ValueRange) {
        for (i: Int in 0..length - 1) {

            month = value.getValues().get(i).get(0).toString().toInt()
            val user = userService.findUserByNumber(value.getValues().get(i).get(1).toString().toLong())
            username = value.getValues().get(i).get(2).toString()
            questName = value.getValues().get(i).get(3).toString()
            achievement = value.getValues().get(i).get(4).toString()
            exp = value.getValues().get(i).get(5).toString().toLong()
            content = value.getValues().get(i).get(6).toString()

            val quest: LeaderQuests = leaderQuestsRepository.save(
                LeaderQuests.toEntity(
                    month,
                    user,
                    questName,
                    achievement,
                    exp,
                    content
                )
            )
        }
    }

    private fun smallerThanSheet(id: Long) {
        deleteById(id)
    }

    private fun biggerThanSheet(sheet: ValueRange, questLength: Int, sheetLength: Int) {
        val user = userService.findUserByNumber(sheet.getValues().get(questLength - 1).get(1).toString().toLong())

        matchValue(sheet, questLength, sheetLength)

        save(
            LeaderQuests.toEntity(
                month,
                user,
                questName,
                achievement,
                exp,
                content
            )
        )
    }

    private fun matchValue(sheet: ValueRange, questLength: Int, sheetLength: Int) {
        month = sheet.getValues().get(questLength - 1).get(0).toString().toInt()
        username = sheet.getValues().get(questLength - 1).get(2).toString()
        questName = sheet.getValues().get(questLength - 1).get(3).toString()
        achievement = sheet.getValues().get(sheetLength - 1).get(4).toString()
        exp = sheet.getValues().get(sheetLength - 1).get(5).toString().toLong() // "" , null 0 ,,
        content = sheet.getValues().get(sheetLength - 1).get(6).toString()
    }

    private fun same(sheetLength: Int, quests: List<LeaderQuests>, value: ValueRange) {
        for (i: Int in 0..sheetLength - 1) {

            month = value.getValues().get(i).get(0).toString().toInt()
            username = value.getValues().get(i).get(2).toString()
            questName = value.getValues().get(i).get(3).toString()
            achievement = value.getValues().get(i).get(4).toString()
            exp = value.getValues().get(i).get(5).toString().toLong()
            content = value.getValues().get(i).get(6).toString()

            if (month != quests.get(i).month) {
                leaderQuestsRepository.updateMonth(month, quests.get(i).id)
            }

            if (questName != quests.get(i).questName) {
                leaderQuestsRepository.updatequestName(questName, quests.get(i).id)
            }

            if (achievement != quests.get(i).achievement) {
                leaderQuestsRepository.updateAchievement(achievement, quests.get(i).id)
            }

            if (exp != quests.get(i).exp) {
                leaderQuestsRepository.updateExp(exp, quests.get(i).id)
            }

            if (content != quests.get(i).content) {
                leaderQuestsRepository.updateContent(content, quests.get(i).id)
            }
        }
    }

}