package kr.or.dohands.dozon.quest.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import kr.or.dohands.dozon.core.responsetemplate.Response
import kr.or.dohands.dozon.exp.controller.data.QuestWithHistoryInQuest
import kr.or.dohands.dozon.quest.api.JobQuestsHistory
import kr.or.dohands.dozon.quest.api.LeaderQuestsHistory
import kr.or.dohands.dozon.quest.api.QuestsApiProvider
import kr.or.dohands.dozon.quest.api.SwordProjectHistory
import kr.or.dohands.dozon.quest.controller.data.*
import kr.or.dohands.dozon.quest.domain.LeaderQuests
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException

@RestController
class QuestsController(
    private val questsApiProvider: QuestsApiProvider,
    private val leaderQuestsHistory: LeaderQuestsHistory,
    private val jobQuestsHistory: JobQuestsHistory,
    private val swordProjectHistory: SwordProjectHistory
) {

    @GetMapping("/quests/{number}")
    @Operation(summary = "[퀘스트 달성 현황] number: 사번을 param으로 던져주시면 그분의 퀘스트 데이터가 조회됩니다.")
    @ApiResponse(responseCode = "200", description = "questType : String,\n" +
            "            exp: Long,\n" +
            "            dayday: String (요일),\n" +
            "            month : String,\n" +
            "            day : String (일 21)" +
            "")
    fun quests(@PathVariable number : Long): Response<QuestsResponse> {
        val response = questsApiProvider.quests(number)
        return Response(200, response, "퀘스트 달성 현황 조회입니다.")
    }

    @GetMapping("/quests/leader/{number}")
    @Operation(summary = "\"grade\": \"Max\",\n" +
            "            \"date\": \"2025-01-17 00:07:11.137733\",\n" +
            "            \"questName\": \"월특근\",\n" +
            "            \"content\": \"5회\",\n" +
            "            \"exp\": 100")
    fun questsByLeader(@PathVariable number : Long): Response<MutableList<QuestWithHistoryInQuest>> {
        val response =leaderQuestsHistory.execute(number)
        return Response(200, response, "리더부여 퀘스트 달성 현황 조회입니다.")
    }

//    @GetMapping("/quests/job/{number}")
//    fun questsByJob(@PathVariable number : Long): Response<MutableList<Quests>> {
//        val response =jobQuestsHistory.execute(number)
//        return Response(200, response, "직무별 퀘스트 달성 현황 조회입니다.")
//    }

    @GetMapping("/quests/sword/{number}")
    @Operation(summary = "\"grade\": \"\",\n" +
            "            \"date\": \"2025-01-17 00:06:26.125994\",\n" +
            "            \"questName\": \"AAA 프로젝트 신설\",\n" +
            "            \"content\": \"Good\",\n" +
            "            \"exp\": 100")
    fun questsBySword(@PathVariable number : Long): Response<MutableList<QuestWithHistoryInQuest>> {
        val response =swordProjectHistory.execute(number)
        return Response(200, response, "전사 프로젝트 달성 현황 조회입니다.")
    }

    @GetMapping("/quests/count/{number}")
    @Operation(summary = "\" [메인] questType\": \"직무별 퀘스트 (종류)\",\n" +
            "                \"clearNumber\": 달성한 개수,\n" +
            "                \"totalNumber\": 총 개수,\n" +
            "                \"questName\": \"주별 직무 과제 / 업무 개선 (퀘스트 부제목)\"")
    @ApiResponse(responseCode = "401", description = "사번을 확인해주세요.")
    fun count(@PathVariable number : Long):Response<QuestCount.Response> {
        if(number == null || number.toString().length < 10){
            throw IllegalArgumentException("사번을 확인해주세요.")
        }
        val response = questsApiProvider.top3(number)
        return Response(200, response, "달성한 퀘스트 횟수 조회입니다.")
    }

    @GetMapping("/quests/graph/{number}")
    @Operation(summary = "생산성 그래프 수치 조회", description = "param에 number를 주시면 생산성 수치들을 list로 드립니다.")
    @ApiResponse(responseCode = "401", description = "사번을 확인해주세요.")
    fun productivity(@PathVariable number : Long): Response<ProductGraph.List> {
        if(number == null || number.toString().length < 10){
            throw IllegalArgumentException("사번을 확인해주세요.")
        }
        val response = questsApiProvider.productivity(number)
        return Response(200, response, "생산성 그래프 수치 조회입니다.")
    }

    // 달성되지 않은 퀘스트 1개 직무별, 리더부여, 전사
    @GetMapping("/quests/ready/{number}")
    fun readyQuest(@PathVariable number: Long): Response<NotClearQuest> {
        val response = questsApiProvider.readyQuest(number)
        return Response(200, response, "달성하지 않은 퀘스트 입니다.")
    }


}