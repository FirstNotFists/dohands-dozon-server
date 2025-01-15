package kr.or.dohands.dozon.user.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface LevelExpTypeRepository: JpaRepository<LevelExpType, Long>, JpaSpecificationExecutor<LevelExpType> {

    @Query("SELECT * FROM dohands.`level-exp-type` where  type = :type and exp < :exp order by exp;", nativeQuery = true)
    fun findNextLevel(type: String, exp: Long) : List<LevelExpType>

    @Query("SELECT * FROM dohands.`level-exp-type` where  type = :type and exp <= :exp order by exp desc;", nativeQuery = true)
    fun findNextLevelNowCheck(type: String, exp: Long) : List<LevelExpType>
//    SELECT * FROM dohands.`level-exp-type` where type = "F" and exp <= 13600 order by exp desc;

    fun findByLevel(level: String): LevelExpType

}