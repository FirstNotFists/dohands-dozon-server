package kr.or.dohands.dozon.quest.match

import jakarta.persistence.EntityManager
import kr.or.dohands.dozon.core.configuration.googlesheet.GoogleSheetsConfiguration
import kr.or.dohands.dozon.exp.service.ExpHistoryService
import kr.or.dohands.dozon.exp.service.ExpService
import kr.or.dohands.dozon.exp.service.GiveExpLeaderQuest
import kr.or.dohands.dozon.quest.domain.*
import kr.or.dohands.dozon.quest.service.HrEvaluationListService
import kr.or.dohands.dozon.quest.service.LeaderQuestsService
import kr.or.dohands.dozon.sheet.service.GoogleSheetsService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import java.io.PipedReader

@Import(GoogleSheetsConfiguration::class)
@Configuration
class SheetIntegrationConfiguration(
    private val leaderQuestsRepository: LeaderQuestsRepository,
    private val jobQuestsRepository: JobQuestsRepository,
    private val swordProjectRepository: SwordProjectRepository,
    private val hrEvaluationListRepository: HrEvaluationListRepository,
    private val googleSheetsService: GoogleSheetsService,
    private val userService: UserService,
    private val leaderQuestsService: LeaderQuestsService,
    private val expService: ExpService,
    private val expHistoryService: ExpHistoryService,
    private val giveExpLeaderQuest: GiveExpLeaderQuest,
    private val entityManager: EntityManager

) {

    @Bean
    fun leaderQuestsMatchService() : LeaderQuestMatch {
        return LeaderQuestMatch(leaderQuestsRepository, googleSheetsService, userService, leaderQuestsService, giveExpLeaderQuest)
    }

    @Bean
    fun jobQuestsMatchService() : JobQuestMatch {
        return JobQuestMatch(googleSheetsService, jobQuestsRepository)
    }

    @Bean
    fun swordProjectMatchService() : SwordProjectMatch {
        return SwordProjectMatch(swordProjectRepository, googleSheetsService, userService)
    }

    @Bean
    fun hrEvaluationMatch() : HrEvaluationMatch {
        return HrEvaluationMatch(googleSheetsService,hrEvaluationListRepository,entityManager)
    }

    @Bean
    fun hrEvaluationMatchService() : HrEvaluationListService {
        return HrEvaluationListService(hrEvaluationListRepository)
    }

    @Bean
    fun entityManager() : EntityManager {
        return entityManager
    }



}