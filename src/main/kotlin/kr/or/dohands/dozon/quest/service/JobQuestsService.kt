package kr.or.dohands.dozon.quest.service

import kr.or.dohands.dozon.quest.domain.JobQuests
import kr.or.dohands.dozon.quest.domain.JobQuestsRepository
import kr.or.dohands.dozon.user.domain.Career
import org.springframework.stereotype.Component

@Component
class JobQuestsService (
    private val jobQuestsRepository: JobQuestsRepository
) {

    fun findByCareer(career: String) : List<JobQuests>{
        return jobQuestsRepository.findByCareer(career)
    }

    fun findById(questId: Long): JobQuests {
        return jobQuestsRepository.findById(questId).get()
    }

    fun findAll(): List<JobQuests> {
        return jobQuestsRepository.findAll()
    }

}