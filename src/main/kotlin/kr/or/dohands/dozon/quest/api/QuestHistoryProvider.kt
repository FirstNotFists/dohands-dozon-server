package kr.or.dohands.dozon.quest.api

import kr.or.dohands.dozon.quest.controller.data.Quests

interface QuestHistoryProvider {
    fun execute(number: Long):MutableList<Quests>
}