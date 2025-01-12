package kr.or.dohands.dozon.notification.service

import com.niamedtech.expo.exposerversdk.ExpoPushNotificationClient
import kr.or.dohands.dozon.notification.request.PushNotification
import kr.or.dohands.dozon.user.service.UserService
import lombok.extern.slf4j.Slf4j
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.springframework.stereotype.Service

@Slf4j
@Service
class NotificationService(private val userService: UserService) : Notification {
    private val host = ExpoPushNotificationClient.builder()
         .setHttpClient(HttpClients.createDefault())
         .build()

    override fun push(id: String) {
        expoPushNotification(id)
    }

    private fun expoPushNotification(id: String) {

        // token 검색 필요
        val user = userService.findUserById(id)

        // 메시지 내용 및 토큰 입력
        val expoPushNotification = PushNotification().apply{
            to = listOf(user.pushToken)
            title = "안녕하세요 정규환님"
            body = "퀘스트가 완료되었습니다. 확인해보세요!"
        }

        host.sendPushNotifications(listOf(expoPushNotification))

    }


    // username 님
    //  ~ 퀘스트 완료되었습니다! 지금 확인해보세요!

}