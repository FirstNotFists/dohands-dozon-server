package kr.or.dohands.dozon.quest.service

import kr.or.dohands.dozon.quest.domain.SwordProject
import kr.or.dohands.dozon.quest.domain.SwordProjectRepository
import kr.or.dohands.dozon.user.domain.User
import org.springframework.stereotype.Service

@Service
class SwordProjectService (
    private val swordProjectRepository: SwordProjectRepository
){
    fun findByNumber(user: User): List<SwordProject> {
        return swordProjectRepository.findByNumber(user)
    }

    fun findById(id: Long) : SwordProject{
        return swordProjectRepository.findById(id).get()
    }

    fun findAll(): List<SwordProject> {
        return swordProjectRepository.findAll()
    }
}