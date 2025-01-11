package kr.or.dohands.dozon.notification.service

import com.niamedtech.expo.exposerversdk.ExpoPushNotificationClient
import kr.or.dohands.dozon.notification.request.PushNotification
import kr.or.dohands.dozon.notification.response.ReceiptResponse
import kr.or.dohands.dozon.notification.response.TicketResponse
import lombok.extern.slf4j.Slf4j
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.core5.http.io.entity.StringEntity
import org.springframework.stereotype.Service
import kotlin.math.log

@Slf4j
@Service
class NotificationService(

): Notification {
     private val host = ExpoPushNotificationClient.builder()
         .setHttpClient(HttpClients.createDefault())
         .build()

    override fun push() {
        expoPushNotification()
    }

    private fun expoPushNotification() {

        // token 검색 필요
        val token: String = "ExponentPushToken[Rvbx83G_XYcHdRIo70DG_K]"
        val token2: String =  "ExponentPushToken[ig62iLPv3vHGnrZIORdxUN]"

        // 메시지 내용 및 토큰 입력
        val expoPushNotification = PushNotification().apply{
            to = listOf(token, token2)
            title = "안녕하세요 정규환님"
            body = "퀘스트가 완료되었습니다. 확인해보세요!"
        }

        host.sendPushNotifications(listOf(expoPushNotification))

    }


    // username 님
    //  ~ 퀘스트 완료되었습니다! 지금 확인해보세요!

}