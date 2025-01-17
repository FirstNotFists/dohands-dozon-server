package kr.or.dohands.dozon.quest.domain

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface LeaderQuestsRepository: JpaRepository<LeaderQuests, Long>, JpaSpecificationExecutor<LeaderQuests> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leader-quests` SET month = :month WHERE id = :id"
        , nativeQuery = true)
    fun updateMonth(month: Int, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leader-quests` SET number = :user WHERE id = :id"
        , nativeQuery = true)
    fun updateNumber(user: User, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leader-quests` SET questName = :questName WHERE id = :id"
        , nativeQuery = true)
    fun updatequestName(questName: String, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leader-quests` SET achievement = :achievement WHERE id = :id"
        , nativeQuery = true)
    fun updateAchievement(achievement: String, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leader-quests` SET exp = :exp WHERE id = :id"
        , nativeQuery = true)
    fun updateExp(exp: Long, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leader-quests` SET content = :content WHERE id = :id"
        , nativeQuery = true)
    fun updateContent(content: String, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leader-quests` SET number = :user WHERE id = :id"
        , nativeQuery = true)
    fun updateUser(user: User, id: Long)

    fun findByNumber(number: User): List<LeaderQuests>

    @Query("select m from `leader-quests` where number = :number order by updateDate limit 6", nativeQuery = true)
    fun findByNumberOrderByUpdateDate(number: User) : List<LeaderQuests>

    @Query("SELECT count(*) FROM dohands.`leader-quests`;", nativeQuery = true)
    fun findCountAll():Long

    @Query("""
    SELECT * FROM dohands.`leader-quests` a 
    WHERE NOT EXISTS (
        SELECT 1 FROM dohands.`leader-quests` b WHERE b.id IN (:numbers) AND b.id = a.id
    )
    LIMIT 1
""", nativeQuery = true)
    fun findNotClear(@Param("numbers") numbers: MutableList<Long?>): LeaderQuests



}