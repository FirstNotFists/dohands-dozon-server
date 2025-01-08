package kr.or.dohands.dozon.quest.domain

import jakarta.persistence.*
import kr.or.dohands.dozon.user.domain.User

@Entity(name = "leader-quests")
class LeaderQuests (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val month: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val number: User,
    val questName: String, // 리더 부여 퀘스트명
    val achievement: String, // 달성내용
    val exp: Long, // 부여 경험치
    val content: String // 비고
){
}