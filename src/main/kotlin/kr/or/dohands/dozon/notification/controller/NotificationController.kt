package kr.or.dohands.dozon.notification.controller

import kr.or.dohands.dozon.notification.request.NotificationRequest
import kr.or.dohands.dozon.notification.service.NotificationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notification")
class NotificationController(
    private val notificationService: NotificationService
) {

    @PostMapping("/send")
    fun push(
        @RequestParam type : String, @RequestParam questId : String
    ): Unit {
        notificationService.push()
    }

}