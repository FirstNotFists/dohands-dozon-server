package kr.or.dohands.dozon.quest.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity(name = "week-quests")
class WeekQuests(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val max: BigDecimal,
    val medium: BigDecimal,
    val week: Int,
    val productivity: BigDecimal
) {

}