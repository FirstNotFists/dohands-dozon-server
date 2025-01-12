package kr.or.dohands.dozon.quest.domain

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

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


}