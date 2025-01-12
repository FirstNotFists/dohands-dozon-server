package kr.or.dohands.dozon.quest.domain

import jakarta.persistence.*
import kr.or.dohands.dozon.user.domain.Career
import kr.or.dohands.dozon.user.domain.User
import java.math.BigDecimal
import java.math.RoundingMode

@Entity(name = "job-quests")
class JobQuests(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,

    @Column(nullable = true)
    val week: Long?,

    @Column(nullable = true)
    val month: Long?,
    val exp : Long,

    val career : String,
    val part : Long,
    val content: String,

    @Column(precision = 10, scale = 3)
    val productivity: BigDecimal

) {
    companion object {
        fun toEntity(
            exp: Long,
            week: Long?,
            month: Long?,
            career: String,
            part: Long,
            content: String,
            productivity: BigDecimal
        ) : JobQuests {
            return JobQuests(
                0,
                week,
                month,
                exp,
                career,
                part,
                content,
                limitScale(productivity)
            )
        }

        private fun limitScale(value: BigDecimal) : BigDecimal {
            return value.setScale(3, RoundingMode.HALF_UP)
        }
    }
}