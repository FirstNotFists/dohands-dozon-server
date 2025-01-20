package kr.or.dohands.dozon.sheet.service

import kr.or.dohands.dozon.exp.service.AutoGrantExp
import kr.or.dohands.dozon.exp.service.GiveExpSwordProject
import kr.or.dohands.dozon.quest.match.HrEvaluationMatch
import kr.or.dohands.dozon.quest.match.SheetIntegrationScan
import kr.or.dohands.dozon.quest.service.AutoResetExpInYear
import kr.or.dohands.dozon.user.service.LevelExpTypeService
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Slf4j
class GoogleSheetsScheduler(
    private val sheetIntegrationScan: SheetIntegrationScan,
    private val autoGrantExp: AutoGrantExp,
    private val autoResetExpInYear: AutoResetExpInYear,
    private val giveExpSwordProject: GiveExpSwordProject,
    private val levelExpTypeService: LevelExpTypeService,
    private val hrEvaluationMatch: HrEvaluationMatch
) {
    private val logger: Logger = LoggerFactory.getLogger(GoogleSheetsScheduler::class.java)

    @Scheduled(fixedRate = 10000)
    fun fetch() {
        logger.info("shcedule running: {}", LocalDateTime.now())
        sheetIntegrationScan.match()
    }

    @Scheduled(cron = "0 0 0 * * *")
    fun exp() {
        logger.info("!!!!! 경험치 일괄지급 : {} ", LocalDateTime.now())
        autoGrantExp.grant()
    }

    @Scheduled(cron = "0 0 0 1 1 *")
    fun reset() {
        logger.info("@@@@@ 올해 초기화 및 경험치 지급 : {}", LocalDateTime.now())
        giveExpSwordProject.levelUp()
        autoResetExpInYear.reset()
    }

    @Scheduled(cron = "0 0 0 1 1,7 *")
    fun hr() {
        logger.info("@@@@@ 인사평가 : {}", LocalDateTime.now())
            try{
            hrEvaluationMatch.match()
            Thread.sleep(10)
            }catch(exception: NumberFormatException){
            }
    }


}