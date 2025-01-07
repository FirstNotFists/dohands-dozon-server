package kr.or.dohands.dozon.exp.domain

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "exp-history-year")
class ExpHistoryYear(

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "number")
    val number: Exp,
    val year: Int,
    val exp: Int
) {

}