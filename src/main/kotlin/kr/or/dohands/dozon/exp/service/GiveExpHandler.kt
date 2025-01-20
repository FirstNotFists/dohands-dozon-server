package kr.or.dohands.dozon.exp.service

import org.springframework.stereotype.Component

@Component
interface GiveExpHandler {

    fun give(questId: Long): Unit

}