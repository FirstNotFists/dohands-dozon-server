package kr.or.dohands.dozon.exp.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Getter
import java.time.LocalDateTime
import java.util.*

@Entity(name = "exp-history")
@AllArgsConstructor
@Getter
class ExpHistory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,

    val number : Long,
    val questType : String, // 리더부여 퀘스트, 직무별 퀘스트, 인사평가, 전사 프로젝트
    val updateDate : LocalDateTime,
    val year : Int,
    var exp : Long,
    val grade : String,

    @Column(nullable = true)
    val questId : Long?

) {
    companion object {
        fun toEntity(
            number : Long,
            questType: String,
            updateDate: LocalDateTime,
            year: Int,
            exp : Long,
            grade : String,
            questId: Long?
        ): ExpHistory {
            return ExpHistory(
                0,
                number,
                questType,
                updateDate,
                year,
                exp,
                grade,
                questId
            )
        }

      }



}