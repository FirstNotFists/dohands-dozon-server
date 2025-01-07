package kr.or.dohands.dozon.quest.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "month-quests")
class MonthQuests(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val month: Int, // 월
    val week: Int, // 주
    val date: String, // 날짜
    val day: Day, // 요일
    val price: Long,
    val laborCosts: Long, // 인건비
    val designFee: Long, // 설계용역비
    val employeeSalaries: Long, // 직원급여
    val retirementBenefits: Long, // 퇴직급여
    val socialInsurance: Long // 4대 보험료


    ) {

}