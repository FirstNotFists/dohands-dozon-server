package kr.or.dohands.dozon.quest.domain

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
interface JobQuestsRepository: JpaRepository<JobQuests, Long>, JpaSpecificationExecutor<JobQuests> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE `job-quests` SET exp = :exp WHERE id = :id"
        , nativeQuery = true)
    fun updateExp(exp: Long, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `job-quests` SET month = :month WHERE id = :id"
        , nativeQuery = true)
    fun updateMonth(month: Long?, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `job-quests` SET career = :career WHERE id = :id"
        , nativeQuery = true)
    fun updateCareer(career: String, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `job-quests` SET part = :part WHERE id = :id"
        , nativeQuery = true)
    fun updatePart(part: Long, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `job-quests` SET productivity = :productivity WHERE id = :id"
        , nativeQuery = true)
    fun updateProductivity(productivity: BigDecimal, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `job-quests` SET week = :week WHERE id = :id"
        , nativeQuery = true)
    fun updateWeek(week: Long?, id: Long)

}