package kr.or.dohands.dozon.service.sheet

import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.model.ValueRange
import com.google.auth.oauth2.GoogleCredentials
import kr.or.dohands.dozon.sheet.service.DriveService
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import kr.or.dohands.dozon.core.configuration.googlesheet.GoogleSheetsConfiguration
import org.assertj.core.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest(classes = [GoogleSheetsConfiguration::class, DriveService::class])
class GoogleSheetsTest @Autowired constructor(
    private val googleSheetsService: GoogleSheetsService,
    private val driveService: DriveService,
) {

    @Test
    fun `시트 연동을 시도한다`() {

        var result: Sheets = googleSheetsService.getSheetsService()
        Assertions.assertThat(result).isNotNull()
        Assertions.assertThat(result.spreadsheets()).isNotNull()
    }

    @Test
    fun `드라이브 연동을 시도한다`() {

        var result = driveService.getDriveService()

        Assertions.assertThat(result).isNotNull()
    }

    @Test
    fun `드라이브에서 엑셀파일을 탐색한다`() {

        var result = driveService.getDriveService()
        println(result.files().get("서비스 기획 상세"))

        Assertions.assertThat(result).isNotNull()
    }


    @Test
    fun `credential이 정상인지 확인한다`() {

        var result: GoogleCredentials = googleSheetsService.loadCredentials()
        Assertions.assertThat(result).isNotNull()
        Assertions.assertThat(result.createScoped()).isNotNull()
    }

    @Test
    fun `경험치 현황 셀 데이터를 로드한다`() {

        val value: ValueRange = googleSheetsService.getValue("경험치 현황", "B13:G14")
        Assertions.assertThat(value).isNotNull()
        println(value.getValues())
    }

    @Test
    fun `인사평가 셀 데이터를 로드한다`() {

        val value = googleSheetsService.getValue("인사평가", "H10:K34")
        Assertions.assertThat(value).isNotNull()
        println(value.getValues())
    }

}



    // 리더부여 퀘스트 생성 기능
    // 리더부여 퀘스트 달성여부 확인
    // MAX/MEDIUM 입력시 계산


