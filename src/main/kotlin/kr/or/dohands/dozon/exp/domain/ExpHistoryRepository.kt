package kr.or.dohands.dozon.exp.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ExpHistoryRepository: JpaRepository<ExpHistory, Long> , JpaSpecificationExecutor<ExpHistory> {


    @Query("SELECT * FROM dohands.`exp-history` where number = :number order by updateDate;", nativeQuery = true)
    fun findExpHistoryByNumber(number: Long) : List<ExpHistory>

}