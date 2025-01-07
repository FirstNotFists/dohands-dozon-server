package kr.or.dohands.dozon.user.domain

import jakarta.persistence.*

@Entity(name = "users")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val number: Long,

    val name: String,
    val joinDate: String,
    val id: String,
    val password: Long,
    val newPassword: String,
    val exp: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val level: LevelExpType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val team: Team,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val career: Career,

    val userType: String

) {



}