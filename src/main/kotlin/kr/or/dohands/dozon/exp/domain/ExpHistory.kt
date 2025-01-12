package kr.or.dohands.dozon.exp.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import lombok.Getter
import java.util.*

@Entity(name = "exp-history")
@AllArgsConstructor
@Getter
class ExpHistory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,

    val number : Long,
    val questType : String,
    val updateDate : Date,
    val year : Int,
    val exp : Long,
    val grade : String

) {


}