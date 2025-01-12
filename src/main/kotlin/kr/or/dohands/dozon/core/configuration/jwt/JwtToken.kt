package kr.or.dohands.dozon.core.configuration.jwt

import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.stereotype.Component

@Component
interface JwtToken {

    fun create(id: String) : String

    fun verify(token: String) : DecodedJWT

    fun getUsername(token: String): String

    fun isValidToken(token: String): Boolean

}