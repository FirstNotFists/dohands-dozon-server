package kr.or.dohands.dozon.quest.domain

import jakarta.persistence.*
import kr.or.dohands.dozon.user.domain.User

@Entity(name = "job-quests")
class JobQuests(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,
    val week: Long,
    val exp : Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "number_number")
    val number : User,
    val content: String
) {
}