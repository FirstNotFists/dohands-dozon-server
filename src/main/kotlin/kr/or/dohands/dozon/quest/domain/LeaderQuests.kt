package kr.or.dohands.dozon.quest.domain

import jakarta.persistence.*
import kr.or.dohands.dozon.user.domain.User

@Entity(name = "leader-quests")
class LeaderQuests (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val number: User,
    val questName: String,
    val projectName: String,
    val exp: Long,
    val content: String

){
}