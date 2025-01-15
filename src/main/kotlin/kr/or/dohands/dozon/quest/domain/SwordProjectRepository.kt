package kr.or.dohands.dozon.quest.domain

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SwordProjectRepository: JpaRepository<SwordProject, Long> , JpaSpecificationExecutor<SwordProject>{

    @Modifying
    @Transactional
    @Query(value = "UPDATE `sword-project` SET date = :date WHERE id = :id"
        , nativeQuery = true)
    fun updateDate(date: String, id: Long)

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE `sword-project` SET number = :User WHERE id = :id"
//        , nativeQuery = true)
//    fun updateUser(user: User, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `sword-project` SET projectName = :projectName WHERE id = :id"
        , nativeQuery = true)
    fun updateProjectName(projectName: String, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `sword-project` SET exp = :exp WHERE id = :id"
        , nativeQuery = true)
    fun updateExp(exp: Long, id: Long)

    @Modifying
    @Transactional
    @Query(value = "UPDATE `sword-project` SET content = :content WHERE id = :id"
        , nativeQuery = true)
    fun updateContent(content: String, id: Long)

    fun findByNumber(number: User): List<SwordProject>


}