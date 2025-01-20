package kr.or.dohands.dozon.quest.service

import kr.or.dohands.dozon.quest.domain.LeaderQuests
import kr.or.dohands.dozon.quest.domain.LeaderQuestsRepository
import kr.or.dohands.dozon.user.domain.User
import org.springframework.stereotype.Service

@Service
class LeaderQuestsService(
        private val leaderQuestsRepository: LeaderQuestsRepository
) {

    fun findAll(): List<LeaderQuests>{
        return leaderQuestsRepository.findAll()
    }

    fun findByUser(user: User) : List<LeaderQuests> {
        return leaderQuestsRepository.findByNumber(user)
    }

    fun findById(questId: Long): LeaderQuests {
        return leaderQuestsRepository.findById(questId).get()
    }

    fun findByNumberOrderByUpdateDate(number: User): List<LeaderQuests> {
        return leaderQuestsRepository.findByNumberOrderByUpdateDate(number)
    }

    fun findCountAll(): Long {
        return leaderQuestsRepository.findCountAll()
    }

    fun findNotClear(numbers: MutableList<Long?>) : LeaderQuests {
        return leaderQuestsRepository.findNotClear(numbers)
    }

}