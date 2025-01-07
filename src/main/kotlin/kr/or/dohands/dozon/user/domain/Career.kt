package kr.or.dohands.dozon.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "career")
class Career(
    @Id
    val name: String,
    val createDate: String
) {

}