package kr.or.dohands.dozon.quest.api

import kr.or.dohands.dozon.quest.controller.data.QuestCount

interface QuestCountProvider {
    fun execute(number: Long) : QuestCount
}