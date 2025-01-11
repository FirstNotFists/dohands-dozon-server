package com.niamedtech.expo.exposerversdk

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import kr.or.dohands.dozon.notification.handler.BaseResponseHandler
import kr.or.dohands.dozon.notification.request.PushNotification
import kr.or.dohands.dozon.notification.request.ReceiptRequest
import kr.or.dohands.dozon.notification.response.ReceiptResponse
import kr.or.dohands.dozon.notification.response.TicketResponse
import kr.or.dohands.dozon.notification.util.ObjectMapperFactory
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.core5.http.io.entity.StringEntity
import java.io.IOException
import java.net.URI

/**
 * Client for synchronous communication with Expo Push Notification Service.
 * See https://docs.expo.dev/push-notifications/sending-notifications/
 */
class ExpoPushNotificationClient private constructor(
    private val baseUri: URI,
    private val httpClient: CloseableHttpClient
) {

    private val objectMapper: ObjectMapper = ObjectMapperFactory().getInstance()

    private val sendResponseHandler =
        BaseResponseHandler(TicketResponse::class.java)

    private val getReceiptHandler =
        BaseResponseHandler(ReceiptResponse::class.java)

    @Throws(IOException::class)
    fun sendPushNotifications(notifications: List<PushNotification>): List<TicketResponse.Ticket> {
        val request = createHttpPostRequest("/push/send", notifications)
        return httpClient.execute(request, sendResponseHandler)
    }

    @Throws(JsonProcessingException::class)
    private fun createHttpPostRequest(subpath: String, requestData: Any): HttpPost {
        val request = HttpPost(URI.create(baseUri.toString() + subpath))
        request.setHeader("Host", "exp.host")
        request.setHeader("accept", "application/json")
        request.setHeader("accept-encoding", "gzip, deflate")
        request.setHeader("content-type", "application/json")

        val json = objectMapper.writeValueAsString(requestData)
        val stringEntity = StringEntity(json)
        request.entity = stringEntity

        return request
    }

    @Throws(IOException::class)
    fun getPushNotificationReceipts(ids: List<String>): Map<String, ReceiptResponse.Receipt> {
        val request = createHttpPostRequest("/push/getReceipts", ReceiptRequest(ids))
        return httpClient.execute(request, getReceiptHandler)
    }

    class Builder {
        private var baseUri: String = "https://exp.host/--/api/v2/"
        private var httpClient: CloseableHttpClient? = null

        fun setBaseUri(baseUri: String): Builder {
            this.baseUri = baseUri
            return this
        }

        fun setHttpClient(httpClient: CloseableHttpClient): Builder {
            this.httpClient = httpClient
            return this
        }

        fun build(): ExpoPushNotificationClient {
            val client = httpClient
                ?: throw IllegalArgumentException("HttpClient must be provided")
            return ExpoPushNotificationClient(URI.create(baseUri), client)
        }
    }

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }
}
