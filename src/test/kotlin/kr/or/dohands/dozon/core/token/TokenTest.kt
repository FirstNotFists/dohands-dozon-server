package kr.or.dohands.dozon.core.token

import com.auth0.jwt.JWT
import kr.or.dohands.dozon.core.configuration.jwt.JwtToken
import kr.or.dohands.dozon.core.configuration.jwt.JwtUtil
import org.assertj.core.api.Assertions
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest(classes = [])
class TokenTest (
) {
    private val secret: String = "your-very-long-and-secure-secret-key-with-512-bits-or-more";
    private val jwtToken: JwtToken = JwtUtil(secret)

    @Test
    fun `토큰을 발급한다` () {
        val token: String = jwtToken.create("20240101")
        println(token)
        Assertions.assertThat(token).isNotNull()
    }

    @Test
    fun `토큰을 발급한 것을 가지고 토큰을 검증한다` () {
        val id = "20240101"
        val token: String = jwtToken.create(id)
        val result = jwtToken.verify(token)

        Assertions.assertThat(JWT.decode(token).claims).isNotNull()
    }

    @Test
    fun `토큰이 파기되었는지 확인한다` () {
        val id = "20240101"
        val token: String = jwtToken.create(id)
        val result = jwtToken.isValidToken(token)

        Assertions.assertThat(result).isTrue() // 파기되지 않았음
        Assertions.assertThat(result).isFalse() // 파기됨
    }

//    @Test
//    fun `` () {
//    }


}