package kr.or.dohands.dozon.service.quest

import kr.or.dohands.dozon.quest.service.AutoResetExpInYear
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class AutoResetTest @Autowired constructor(
    private val autoResetExpInYear: AutoResetExpInYear
) {

    @Test
    fun `매년 1월 1일 총 누적 경험치를 정산하고 초기화한다`() {
        // exp year id number
        autoResetExpInYear.reset()
    }
}