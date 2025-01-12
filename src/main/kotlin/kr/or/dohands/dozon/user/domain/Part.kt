package kr.or.dohands.dozon.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "part")
class Part(

    @Id
    val name: String,
    val updateDate: String

) {


}