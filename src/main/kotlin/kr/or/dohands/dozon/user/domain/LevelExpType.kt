package kr.or.dohands.dozon.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "level-exp-type")
class LevelExpType(

    @Id
    val level: String,
    val exp: Long,
    val type: String
) {

}