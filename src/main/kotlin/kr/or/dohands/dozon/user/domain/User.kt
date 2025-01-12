package kr.or.dohands.dozon.user.domain

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter

@Entity(name = "users")
@AllArgsConstructor
@Getter
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val number: Long,

    val name: String,
    val joinDate: String,
    val id: String,
    val password: String,
    val newPassword: String,
    val exp: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val level: LevelExpType,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val part: Part,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val career: Career,

    val userType: UserType = UserType.User,

    val pushToken: String

) {

}