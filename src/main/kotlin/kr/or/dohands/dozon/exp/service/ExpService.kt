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

    fun findTotalExpByNumber(user: User): Long?{
        return expRepository.findtotalExpByNumber(user)
    }

    fun findExpInYear(user: User): Long? {
        return findTotalExpByNumber(user)
    }

    fun findByNumber(user: User) : Exp {
        return expRepository.findExpByNumber(user)
    }

    fun updateFirstEvaluation(number: Long, exp: Long) {
        expRepository.updateFirstEvaluation(number, exp)
    }

    fun updateExpByNumber(exp:Long, number: Long): Unit{
        expRepository.updateExpByNumber(exp, number)
    }

    fun updateLeaderQuestExpByNumber(exp:Long, number: Long): Unit{
        expRepository.updateLeaderQuestExpByNumber(exp, number)
    }

    fun findtotalExpByNumber(user: User): Long? {
        return expRepository.findtotalExpByNumber(user)
    }

    fun findExpByNumber(number: User): Exp {
        return expRepository.findExpByNumber(number)
    }

    fun updateJobQuestExpByNumber(exp: Long, number: Long) {
        expRepository.updateJobQuestExpByNumber(exp, number)
    }

    fun findExpByUserNumber(number: Long):Exp{
        return expRepository.findExpByUserNumber(number)
    }

    fun updateSwordProjectExpByNumber(exp: Long, number: Long) {
        expRepository.updateSwordProjectExpByNumber(exp, number)
    }

    fun findAll(): List<Exp> {
        return expRepository.findAll()
    }

    fun updateSecondEvaluation(exp: Long, number: Long){
        expRepository.updateSecondEvaluation(exp, number)
    }

    fun updateYear(date: Long, number: Long){
        expRepository.updateYear(date, number)
    }

}