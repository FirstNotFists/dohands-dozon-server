package kr.or.dohands.dozon.exp.domain

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ExpHistoryRepository: JpaRepository<ExpHistory, Long> , JpaSpecificationExecutor<ExpHistory> {


    @Query("SELECT * FROM dohands.`exp-history` where number = :number order by updateDate;", nativeQuery = true)
    fun findExpHistoryByNumber(number: Long) : List<ExpHistory>

    @Query("SELECT * FROM dohands.`exp-history` a where a.year = :year and a.questType = :questType and a.number = :number and grade = :grade;", nativeQuery = true)
    fun findByHistory(year: Int, questType: String, number: Long, grade: String) : ExpHistory?

    fun findByQuestId(id: Long) : Boolean

    @Modifying
    @Transactional
    @Query("update `exp-history` set exp = :exp where id = :id", nativeQuery = true)
    fun updateExpHistory(exp: Long, id: Long)

    fun findByQuestIdAndQuestType(questId: Long, questType: String) : List<ExpHistory>



}