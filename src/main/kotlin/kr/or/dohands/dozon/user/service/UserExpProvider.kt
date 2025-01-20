package kr.or.dohands.dozon.user.service

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.exp.service.ExpService
import org.springframework.stereotype.Component

@Component
class UserExpProvider(
    private val userService: UserService
) {

    fun giveExp(exp: Long, number: Long): Unit {
        userService.updateExp(exp, number)
    }


}