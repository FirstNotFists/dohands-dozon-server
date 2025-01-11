package kr.or.dohands.dozon.notification

import com.niamedtech.expo.exposerversdk.ExpoPushNotificationClient
import kr.or.dohands.dozon.notification.request.PushNotification
import kr.or.dohands.dozon.notification.response.ReceiptResponse
import kr.or.dohands.dozon.notification.response.TicketResponse
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockserver.integration.ClientAndServer
import org.mockserver.junit.jupiter.MockServerExtension
import org.mockserver.matchers.Times
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import kotlin.test.Test

@ExtendWith(MockServerExtension::class)
class NotificationTest(

) {

    companion object{
        private val EXPO_NOTIFICATION_TOKEN = "ExponentPushToken[Rvbx83G_XYcHdRIo70DG_K]"
        private val EXPO_NOTIFICATION_TOKEN_2 = "ExponentPushToken[ig62iLPv3vHGnrZIORdxUN]"
    }

    private lateinit var client: ClientAndServer
    private lateinit var httpClient: CloseableHttpClient
    private lateinit var testee: ExpoPushNotificationClient

    @BeforeEach
    fun setUp(client: ClientAndServer) {
        this.client = client
        httpClient = HttpClients.createDefault()

        testee = ExpoPushNotificationClient.builder()
//            .setBaseUri("http://localhost" + client.localPort)
            .setHttpClient(httpClient)
            .build()
    }


    @Test
    fun `알림을 전송한다`() {
        client
            .`when`(HttpRequest.request().withMethod("POST").withPath("/push/send"),
                Times.exactly(1))
            .respond(
                HttpResponse.response()
                    .withStatusCode(200)
                    .withBody(ResponseTestFixture.GET_RECEIPT_OK_MULTIPLE_RESPONSE)
            )

        val expoPushNotification = PushNotification().apply {
            to = listOf(EXPO_NOTIFICATION_TOKEN, EXPO_NOTIFICATION_TOKEN_2)
            title = "title"
            body = "content"
        }


        val tickets: List<TicketResponse.Ticket> =
            testee.sendPushNotifications(listOf(expoPushNotification))


        val ids: List<String> = tickets.map{ it.id }

        val receipts: Map<String, ReceiptResponse.Receipt> =
            testee.getPushNotificationReceipts(ids)

        Assertions.assertThat(receipts).isNotNull
    }

    // 퀘스트 완료 알림을 진행한다.. 흠..

}