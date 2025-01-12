package kr.or.dohands.dozon.sheet.controller

import kr.or.dohands.dozon.quest.service.JobQuestMatchService
import kr.or.dohands.dozon.quest.service.LeaderQuestMatchService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/webhook")
class WebhookController(
    private val leaderQuestMatchService: LeaderQuestMatchService,
    private val jobQuestMatchService: JobQuestMatchService
) {

    @PostMapping
    fun handleWebhook(@RequestHeader headers: Map<String, String>, @RequestBody(required = false) body: String?){
        println("스프레드시트 변경감지!")

        leaderQuestMatchService.match() // 리더부여 퀘스트 데이터 매칭
        jobQuestMatchService.match()
    }


}