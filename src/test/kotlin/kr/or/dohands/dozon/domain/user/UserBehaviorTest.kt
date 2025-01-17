package kr.or.dohands.dozon.domain.user

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.domain.UserRepository
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserBehaviorTest (
    private val userRepository: UserRepository
): BehaviorSpec({

    Given("아이디 및 비밀번호가 주어졌을 떄"){
        val id = "jisookim"
        val password = "1111"

        When("설정된 비밀번호가 없다면"){
            val user: User = userRepository.findUserById(id) ?: throw NoSuchElementException("존재하지 않는 사원입니다.")

            if(user.newPassword == null){
                Then("기본 비밀번호와 매치를 시도한다"){
                    password shouldBe user.password
                }
            }
        }
    }
})