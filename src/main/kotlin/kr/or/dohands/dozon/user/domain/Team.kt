package kr.or.dohands.dozon.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "team")
class Team(

    @Id
    val name: String,
    val updateDate: String

) {


}