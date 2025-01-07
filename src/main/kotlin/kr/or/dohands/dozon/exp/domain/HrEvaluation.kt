package kr.or.dohands.dozon.exp.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "hr-evaluation")
class HrEvaluation(

    @Id
    val grade: String,
    val exp: Long
) {


}