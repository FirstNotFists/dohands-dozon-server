package kr.or.dohands.dozon.exp.domain

import jakarta.persistence.*

@Entity(name = "exp-history-year")
class ExpHistoryYear(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "number", referencedColumnName = "number_number")
    val number: Exp,
    val year: Int,
    val exp: Int
) {

}