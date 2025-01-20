package kr.or.dohands.dozon.exp.service

import kr.or.dohands.dozon.notification.service.NotificationService
import kr.or.dohands.dozon.quest.service.JobQuestsService
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import kr.or.dohands.dozon.quest.service.SwordProjectService
import org.springframework.stereotype.Component

@Component
class AutoGrantExp(
    private val jobQuestsService: JobQuestsService,
    private val leaderQuestsService: LeaderQuestsService,
    private val swordProjectService: SwordProjectService,
    private val giveExpJobQuest: GiveExpJobQuest,
    private val giveExpLeaderQuest: GiveExpLeaderQuest,
    private val giveExpSwordProject: GiveExpSwordProject,
) {

    fun grant() {
        jobQuests()
        leaderQuests()
        swordProjects()
    }

    fun jobQuests(){
        val list = jobQuestsService.findAll()
        val length = list.size

        for(i : Int in 0..<length){
            giveExpJobQuest.give(list.get(0).id)
        }
    }

    fun leaderQuests(){
        val list = leaderQuestsService.findAll()
        val length = list.size

        for(i : Int in 0..<length){
            giveExpLeaderQuest.give(list.get(i).id)
        }
    }

    fun swordProjects(){
        val list = swordProjectService.findAll()
        val length = list.size

        for(i : Int in 0..<length){
            giveExpSwordProject.give(list.get(i).id)
        }
    }
}