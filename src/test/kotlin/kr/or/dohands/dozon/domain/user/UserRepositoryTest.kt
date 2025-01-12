package kr.or.dohands.dozon.domain.user

import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.domain.UserRepository
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import kotlin.test.Test

@SpringBootTest
class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository
){

    @Test
    fun `특정 유저를 검색한다`() {

        var id: Long = 2021030101

        val user: User = userRepository.findByIdOrNull(id = id) ?: throw NoSuchElementException("해당 유저가 존재하지 않습니다.")

        Assertions.assertThat(user).isNotNull;
        Assertions.assertThat(user.id.length).isNotEqualTo(0);
    }

    @Test
    fun `문자열인 아이디를 입력받아 검색한다`() {

        var id = "jisookim"

        val user: User = userRepository.findUserById(id = id) ?: throw NoSuchElementException("존재하지 않는 사원입니다.")

        Assertions.assertThat(user).isNotNull;
        println(user.name)
        Assertions.assertThat(user.id.length).isNotEqualTo(0);
    }

    @Test
    fun `패스워드를 변경하지 않은 유저가 로그인을 시도한다`() {

        var id = "jisookim"
        var password = "1111"

        val user: User = userRepository.findUserById(id = id) ?: throw NoSuchElementException("존재하지 않는 사원입니다.")

        Assertions.assertThat(user.newPassword).isNull()
        Assertions.assertThat(user.password).isEqualTo(password)
    }

    @Test
    fun `패스워드를 변경한 유저가 기본 패스워드로 로그인을 시도한다`() {

        var id = "equal"
        var password = "1111"

        val user: User = userRepository.findUserById(id = id) ?: throw NoSuchElementException("존재하지 않는 사원입니다.")

        Assertions.assertThat(user.newPassword).isNotNull()
        Assertions.assertThat(user.newPassword).isNotEqualTo(password)
    }

    @Test
    fun `패스워드를 변경한 유저가 변경했던 패스워드로 로그인을 시도한다`() {

        var id = "equal"
        var password = "equal1234"

        val user: User = userRepository.findUserById(id = id) ?: throw NoSuchElementException("존재하지 않는 사원입니다.")

        Assertions.assertThat(user.newPassword).isNotNull()
        Assertions.assertThat(user.newPassword).isEqualTo(password)
    }

    @Test
    fun `아이디 및 비밀번호를 입력받아 로그인을 시도한다`() {

        var id: Long = 2021030101

        val user: User = userRepository.findByIdOrNull(id = id) ?: throw NoSuchElementException("해당 유저가 존재하지 않습니다.")

        Assertions.assertThat(user).isNotNull;
        Assertions.assertThat(user.id.length).isNotEqualTo(0);
    }
}