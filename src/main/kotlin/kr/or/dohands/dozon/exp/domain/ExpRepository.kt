package kr.or.dohands.dozon.exp.domain

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ExpRepository: JpaRepository<Exp, Long>, JpaSpecificationExecutor<Exp> {

    fun findExpByNumber(number: User) : Exp

    @Modifying
    @Transactional
    @Query("update exp m set m.totalExp = :exp where m.number.number = :number")
    fun updateExpByNumber(exp: Long, number: Long): Int

    @Modifying
    @Transactional
    @Query("update exp m set m.leaderQuests = :exp where m.number.number = :number")
    fun updateLeaderQuestExpByNumber(exp: Long, number: Long)


    @Query("select m.totalExp from exp m where m.number = :number")
    fun findtotalExpByNumber(number: User) : Long

    @Query("SELECT b.number, b.name, b.career_name, b.part_name, b.level_level," +
            "a.totalExp, a.firstEvaluation, a.secondEvaluation, a.jobQuests," +
            " a.leaderQuests, a.swordProject FROM dohands.exp a inner join " +
            "dohands.users b on a.number_number = b.number;", nativeQuery = true)
    fun todayExp(): Exp

    @Modifying
    @Transactional
    @Query("update exp m set m.firstEvaluation = :exp where m.number.number = :number")
    fun updateFirstEvaluation(number: Long, exp: Long) : Int

    @Modifying
    @Transactional
    @Query("update exp m set m.jobQuests = :exp where m.number.number = :number")
    fun updateJobQuestExpByNumber(exp: Long, number: Long)


    @Query("select m from exp m where m.number.number = :number")
    fun findExpByUserNumber(number: Long) : Exp

    @Modifying
    @Transactional
    @Query("update exp m set m.swordProject = :exp where m.number.number = :number")
    fun updateSwordProjectExpByNumber(exp: Long, number: Long) :Unit

    @Modifying
    @Transactional
    @Query("update exp m set m.secondEvaluation = :exp where m.number.number = :number")
    fun updateSecondEvaluation(exp: Long, number: Long)

    @Modifying
    @Transactional
    @Query("update exp m set m.year = :year where m.number.number = :number")
    fun updateYear(year: Long, number: Long)


}