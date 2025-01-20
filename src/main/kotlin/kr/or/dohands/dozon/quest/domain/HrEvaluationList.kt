package kr.or.dohands.dozon.quest.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity(name = "hr-evaluation-list")
class HrEvaluationList(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    val exp : Long,
    val grade : String,
    val number : Long,
    val year : Int,
    val date : String

    ) {
    companion object {
        fun toEntity(number: Long, grade: String, exp: Long, date: String): HrEvaluationList {
            return HrEvaluationList(
                0,
                exp,
                grade,
                number,
                LocalDateTime.now().year,
                date
            )
        }
    }


}