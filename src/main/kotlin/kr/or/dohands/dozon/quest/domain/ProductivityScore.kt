package kr.or.dohands.dozon.quest.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "productivity-score")
class ProductivityScore(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val grade: String,
    val score: Int
) {
}