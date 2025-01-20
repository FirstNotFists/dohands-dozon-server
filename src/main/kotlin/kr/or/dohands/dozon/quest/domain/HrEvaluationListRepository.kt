package kr.or.dohands.dozon.quest.domain

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface HrEvaluationListRepository: JpaRepository<HrEvaluationList, Long> ,
    JpaSpecificationExecutor<HrEvaluationList>
{

    @Modifying
    @Transactional
    @Query(value = "UPDATE `hr-evaluation-list` SET number = :number WHERE id = :id"
        , nativeQuery = true)
    fun updateNumber(number: Long, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `hr-evaluation-list` SET grade = :grade WHERE id = :id"
        , nativeQuery = true)
    fun updateGrade(grade: String, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `hr-evaluation-list` SET exp = :exp WHERE id = :id"
        , nativeQuery = true)
    fun updateExp(exp: Long, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `hr-evaluation-list` SET date = :date WHERE id = :id"
        , nativeQuery = true)
    fun updateDate(date: String, id: Long)


}