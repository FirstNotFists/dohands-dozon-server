package kr.or.dohands.dozon.service

import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.ValueRange
import com.google.auth.oauth2.GoogleCredentials
import kr.or.dohands.dozon.sheet.config.GoogleSheetsConfiguration
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest(
    classes = [GoogleSheetsConfiguration::class]
)
class GoogleSheetsTest @Autowired constructor(
    private val googleSheetsService: GoogleSheetsService,
) {
    @Test
    fun `시트 연동을 시도한다` () {

        var result : Sheets  = googleSheetsService.getSheetsService()
        Assertions.assertThat(result).isNotNull()
        Assertions.assertThat(result.spreadsheets()).isNotNull()
    }

    @Test
    fun `credential이 정상인지 확인한다` () {

        var result: GoogleCredentials = googleSheetsService.loadCredentials()
        Assertions.assertThat(result).isNotNull()
        Assertions.assertThat(result.createScoped()).isNotNull()
    }

    @Test
    fun `경험치 현황 셀 데이터를 로드한다` () {

        val value: ValueRange = googleSheetsService.getValue("경험치 현황", "B13:G14")
        Assertions.assertThat(value).isNotNull()
        println(value.getValues())
    }

    @Test
    fun `인사평가 셀 데이터를 로드한다` () {

        val value = googleSheetsService.getValue("인사평가", "H10:K34")
        Assertions.assertThat(value).isNotNull()
        println(value.getValues())
    }

    // J13:S378
    @Test
    fun `직무별 퀘스트 셀 데이터를 로드한다` () {

        val value: ValueRange = googleSheetsService.getValue("직무별 퀘스트", "J13:S378")

        //[월, 주, 날짜, 요일, 매출, 인건비, 설계용역비, 직원급여, 퇴직급여, 4대보험료],
        //[1, 1, 25-1-1, Wed, 10, 4, 1, 1, 1, 1]

        Assertions.assertThat(value).isNotNull()
//        Assertions.assertThat(value.getValues().size).isEqualTo(365)
        println(value.getValues().size)
//        Assertions.assertThat(value.getValues().indexOf(value.getValues().size))
        println(value.getValues().get(value.getValues().lastIndex))
    }

}