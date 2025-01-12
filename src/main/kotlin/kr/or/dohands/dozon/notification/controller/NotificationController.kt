package kr.or.dohands.dozon.notification.controller

import kr.or.dohands.dozon.notification.request.NotificationRequest
import kr.or.dohands.dozon.notification.service.Notification
import kr.or.dohands.dozon.notification.service.NotificationService
import kr.or.dohands.dozon.quest.service.JobQuestMatchService
import kr.or.dohands.dozon.quest.service.JobQuestService
import kr.or.dohands.dozon.user.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/push")
class NotificationController(
    private val notificationService: NotificationService,
    private val jobQuestMatchService: JobQuestMatchService,
    private val userService: UserService
) {
    private val notification: Notification = NotificationService(userService)

    @PostMapping
    fun push(
        @RequestParam type : String, @RequestParam questId : String
    ): Unit {

//        notificationService.push()
//        jobQuestMatchService.match()

    }

    // 푸시알림 테스트
    @PostMapping("/send")
    fun send(
        @RequestParam id: String
//        @RequestParam type : String, @RequestParam questId : String
    ): Unit {
        notification.push(id)
    }

}