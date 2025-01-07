package kr.or.dohands.dozon.exp.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "exp-history-week")
class ExpHistoryWeek(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "number")
    val expId: Exp,
    val week: Int,
    val exp: Int,
    val createDate: String,
) {


}