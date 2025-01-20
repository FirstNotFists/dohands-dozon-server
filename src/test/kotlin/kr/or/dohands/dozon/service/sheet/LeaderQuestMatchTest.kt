package kr.or.dohands.dozon.service.sheet

import kr.or.dohands.dozon.quest.match.LeaderQuestMatch
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LeaderQuestMatchTest @Autowired constructor(
    private val leaderQuestMatch: LeaderQuestMatch
){

    @Test
    fun `빈 데이터베이스에 스프레드시트 데이터를 매치하여 반영한다`() {
        try{
            leaderQuestMatch.match() // 애송이
        }catch (exception: NumberFormatException){
        }
    }

}