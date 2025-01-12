package kr.or.dohands.dozon.service.sheet

import kr.or.dohands.dozon.quest.service.JobQuestMatchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class JobquestMatchServiceTest @Autowired constructor(
    private val jobQuestMatchService: JobQuestMatchService
) {

    @Test
    fun `직무별 퀘스트 시트를 로드하여 데이터를 매치하여 갱신한다`() {
        jobQuestMatchService.match()
    }

}