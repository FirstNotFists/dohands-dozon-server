package kr.or.dohands.dozon.domain.exp

import kr.or.dohands.dozon.exp.domain.Exp
import kr.or.dohands.dozon.exp.domain.ExpRepository
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.service.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.math.exp

@SpringBootTest
class ExpTest @Autowired constructor(
    private val expRepository: ExpRepository,
    private val userService: UserService
) {


    @Test
    fun `올해 누적경험치를 조회한다`() {
        val number: Long = 2023010101
        val user: User = userService.findUserByNumber(number)
        val exp: Long = expRepository.findtotalExpByNumber(user)

        Assertions.assertThat(exp).isNotNull
        Assertions.assertThat(exp).isNotEqualTo(0)
    }

//    @Test
//    fun `팀원별 올해 경험치 현황을 조회한다`() {
//        val number: Long = 2023010101
//        val result = expRepository.todayExp()
//
//        println(result)
//
//        Assertions.assertThat(result).isNotNull
//    }


    }