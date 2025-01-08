package kr.or.dohands.dozon.quest.domain

import jakarta.persistence.*
import kr.or.dohands.dozon.user.domain.User

@Entity(name = "sword-project")
class SwordProject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    val number: User,
    val date: String, // 월-일
    val projectName: String, // 전사 프로젝트명
    val exp: Long, // 부여 경험치
    val content: String // 비고

    ) {

}