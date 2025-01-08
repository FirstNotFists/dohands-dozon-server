package kr.or.dohands.dozon.domain

import kr.or.dohands.dozon.user.domain.User
import kr.or.dohands.dozon.user.domain.UserRepository
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import kotlin.test.Test

@SpringBootTest
class UserTest @Autowired constructor(
    private val userRepository: UserRepository
){

    @Test
    fun `특정 유저를 검색한다`() {

        var id: Long = 2021030101

        val user: User = userRepository.findByIdOrNull(id = id) ?: throw NoSuchElementException("해당 유저가 존재하지 않습니다.")

        Assertions.assertThat(user).isNotNull;
        Assertions.assertThat(user.id.length).isNotEqualTo(0);

    }
}