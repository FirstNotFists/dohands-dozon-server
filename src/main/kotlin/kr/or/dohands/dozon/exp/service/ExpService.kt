package kr.or.dohands.dozon.exp.service

import kr.or.dohands.dozon.exp.domain.Exp
import kr.or.dohands.dozon.exp.domain.ExpRepository
import kr.or.dohands.dozon.user.domain.LevelExpType
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.stereotype.Service
import kotlin.math.exp

@Service
class ExpService(
    private val expRepository: ExpRepository,
) {

    fun findTotalExpByNumber(user: User): Long{
        return expRepository.findtotalExpByNumber(user)
    }

    fun findExpInYear(user: User): Long {
        return findTotalExpByNumber(user)
    }



}