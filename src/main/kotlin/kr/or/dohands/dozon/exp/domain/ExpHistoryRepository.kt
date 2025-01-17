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

    @Query("select * from `exp-history` where number = :number order by updateDate limit 6", nativeQuery = true)
    fun findByNumberOrderByUpdateDate(number: Long) : List<ExpHistory>

    @Query("select * from `exp-history` where number = :number and questType = :questType order by updateDate limit 6", nativeQuery = true)
    fun findByQuestTypeAndNumberOrderByUpdateDate(number: Long, questType: String) : List<ExpHistory>

    @Query("select count(*) from `exp-history` a where questType = :questType and number = :number limit 1", nativeQuery = true)
    fun findCountByQuestTypeAndNumberHighExp(questType: String, number: Long) : Long

    @Query("SELECT a.exp, b.content, b.questName, a.updateDate, a.grade FROM dohands.`exp-history` a INNER JOIN dohands.`leader-quests` b ON a.number = b.number_number WHERE a.questType = :questType AND a.number = :number ORDER BY a.updateDate limit 6;", nativeQuery = true)
    fun findExpHistoryWithLeaderQuests(questType: String, number: Long) : List<Array<Any>>

    @Query("SELECT a.exp, b.content, b.projectName, a.updateDate, a.grade FROM dohands.`exp-history` a INNER JOIN dohands.`sword-project` b ON a.number = b.number_number WHERE a.questType = :questType AND a.number = :number ORDER BY a.updateDate limit 6;", nativeQuery = true)
    fun findExpHistoryWithSwordProjects(questType: String, number: Long) : List<Array<Any>>


}