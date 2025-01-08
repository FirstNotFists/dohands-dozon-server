package kr.or.dohands.dozon.exp.domain

import jakarta.persistence.*
import kr.or.dohands.dozon.user.domain.User

@Entity(name = "exp")
class Exp(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val totalExp: Long,
    val jobQuests: Long,
    val leaderQuests: Long,
    val swordProject: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val number: User,

    val firstEvaluation: Long,
    val secondEvaluation: Long,
    val year: Long

) {
}