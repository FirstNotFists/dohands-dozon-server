package kr.or.dohands.dozon.user.domain

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
interface UserRepository: JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    fun findUserById(id: String): User

    fun findUserByNumber(number: Long): User

    @Modifying
    @Transactional
    @Query("update users m set m.pushToken = :token where m.id = :id")
    fun updateTokenById(id: String, token: String) : Int

    @Modifying
    @Transactional
    @Query("update users m set m.exp = :exp where m.number = :number")
    fun updateExpByNumber(exp: Long, number: Long) : Int

    @Modifying
    @Transactional
    @Query("update users m set m.number = :user where m.id = :id")
    fun updateUserById(id: String, user: User): Int

    @Modifying
    @Transactional
    @Query("update users m set m.newPassword = :password where m.id = :id")
    fun updatePasswordById(id: String, password:String){

    }

    @Modifying
    @Transactional
    @Query("update users m set m.level = :level where m.number = :number")
    fun updateByLevel(level: LevelExpType, number: Long)

    fun findUserByCareer(career: Career): List<User>

}
