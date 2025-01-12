package kr.or.dohands.dozon.service.user

import jakarta.transaction.Transactional
import kr.or.dohands.dozon.user.controller.data.MyPageResponse
import kr.or.dohands.dozon.user.controller.data.SignInRequest
import kr.or.dohands.dozon.user.service.UserService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
) {


    @Test
    @Transactional
    fun `패스워드를 변경하지 않은 유저가 로그인을 시도한다`() {
        var id = "jisookim"
        var password = "1111"
        var pushToken = "31231"
        var anotherPassword = "1234"

        val signInRequest = SignInRequest(id, password, pushToken)

        val result = userService.signIn(signInRequest)

        Assertions.assertThat(result).isNotNull
//        Assertions.assertThatThrownBy {SignInRequest(id, anotherPassword)}
        Assertions.assertThat(result?.name?.length).isNotEqualTo(0)
    }

    @Test
    fun `다음 레벨까지 필요한 경험치를 조회한다`() {
        val id = "minsukim"

        val result: MyPageResponse = userService.myPage(id)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result.level::class).isEqualTo(String::class)
        Assertions.assertThat(result.needExp::class).isEqualTo(Long::class)
        Assertions.assertThat(result.nextLevelExp::class).isEqualTo(Long::class)
    }

    }