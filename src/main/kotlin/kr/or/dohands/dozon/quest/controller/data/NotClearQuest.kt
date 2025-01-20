package kr.or.dohands.dozon.quest.controller.data

import kr.or.dohands.dozon.quest.domain.LeaderQuests

data class NotClearQuest(
    val data: LeaderQuests
) {
    data class List(
        val data: MutableList<Any>
    ){

    }
    companion object{
        fun from (list:LeaderQuests): NotClearQuest{
            return NotClearQuest(
                list
            )
        }
    }
}