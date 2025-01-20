package kr.or.dohands.dozon.quest.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ProductivityScoreRepository : JpaRepository<ProductivityScore, Long> ,
    JpaSpecificationExecutor<ProductivityScore> {
}