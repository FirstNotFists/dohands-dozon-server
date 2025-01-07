package kr.or.dohands.dozon.exp.domain

import jakarta.persistence.*
import kr.or.dohands.dozon.user.domain.User

@Entity(name = "exp")
class Exp(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val totalExp: Long,
    val roleQuests: Long,
    val leaderQuests: Long,
    val companyProject: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val number: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val firstEvaluation: HrEvaluation,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val secondEvaluation: HrEvaluation,

    val year: Long


) {



}