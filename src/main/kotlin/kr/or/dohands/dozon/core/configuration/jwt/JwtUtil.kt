package kr.or.dohands.dozon.core.configuration.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC512
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil(
    private val secret: String = "your-very-long-and-secure-secret-key-with-512-bits-or-more",
    private val issuer: String = "anonymous",
    private val date: Date = Date(),
    private val tokenExpireTime: Long = 3600000

) : JwtToken {

    private val algorithm = HMAC512(secret)

    override fun create(id: String): String{
        return createToken(id)
    }

    override fun isValidToken(token: String): Boolean {
        return (!isTokenExpired(token))
    }

    override fun getUsername(token: String): String {
        return getClaims(token)?.get("userId").toString()
    }

    override fun verify(token: String): DecodedJWT {
        val verifier = JWT.require(algorithm)
            .build()
        return verifier.verify(token)
    }

    private fun createToken(id: String): String {
        return JWT.create().apply {
            withClaim("userId", id)
            withClaim("role", "user")
            withIssuer(issuer)
            withIssuedAt(date)
            withExpiresAt(Date(date.time+tokenExpireTime))
        }.sign(algorithm)
    }

    private fun getClaims(token: String): MutableMap<String, Claim>? {
        return JWT.decode(token).claims
    }

    private fun getExpirationDate(token: String) : Date {
        val expiration = JWT.decode(token).expiresAt
        return expiration
    }

    private fun isTokenExpired (token : String) : Boolean {
        return getExpirationDate(token).before(Date())
    }

}