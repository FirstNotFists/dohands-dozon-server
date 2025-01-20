package kr.or.dohands.dozon.quest.match

import org.springframework.context.annotation.Import
import org.springframework.stereotype.Component

@Component
@Import(SheetIntegrationConfiguration::class)
class SheetIntegrationScan (
    private val leaderQuestMatch: LeaderQuestMatch,
    private val jobQuestMatch: JobQuestMatch,
    private val swordProjectMatch: SwordProjectMatch,
    private val hrEvaluationMatch: HrEvaluationMatch
): SheetMatch {

        override fun match() {
            try{
                leaderQuestMatch.match()
                Thread.sleep(10)
            }catch(exception: NumberFormatException){
            }

            try{
                jobQuestMatch.match()
                Thread.sleep(10)
            }catch(exception: NumberFormatException){
            }

            try{
                swordProjectMatch.match()
                Thread.sleep(10)
            }catch(exception: NumberFormatException){
            }

//            try{
//                hrEvaluationMatch.match()
//                Thread.sleep(10)
//            }catch(exception: NumberFormatException){
//            }
        }

}