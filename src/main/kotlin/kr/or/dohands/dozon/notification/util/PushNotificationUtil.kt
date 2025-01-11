package kr.or.dohands.dozon.notification.util

import kr.or.dohands.dozon.notification.request.PushNotification


object PushNotificationUtil {

    private const val PUSH_NOTIFICATION_CHUNK_LIMIT = 100
    private const val PUSH_NOTIFICATION_RECEIPT_CHUNK_LIMIT = 300

    fun isExponentPushToken(token: String): Boolean {
        val prefixA = "ExponentPushToken["
        val prefixB = "ExpoPushToken["
        val postfix = "]"
        val regex = "[a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12}"

        if (token.matches(regex.toRegex())) {
            return true
        }

        if (!token.endsWith(postfix)) {
            return false
        }

        return token.startsWith(prefixA) || token.startsWith(prefixB)
    }

    fun chunkPushNotificationReceiptIds(receiptIds: List<String>): List<List<String>> {
        return chunkItems(receiptIds, PUSH_NOTIFICATION_RECEIPT_CHUNK_LIMIT)
    }

    fun <T> chunkItems(items: List<T>, chunkSize: Int): List<List<T>> {
        val chunks = mutableListOf<List<T>>()
        var chunk = mutableListOf<T>()
        for (item in items) {
            chunk.add(item)
            if (chunk.size >= chunkSize) {
                chunks.add(chunk)
                chunk = mutableListOf()
            }
        }

        if (chunk.isNotEmpty()) {
            chunks.add(chunk)
        }
        return chunks
    }

    fun chunkPushNotifications(messages: List<PushNotification>): List<List<PushNotification>> {
        val chunks = mutableListOf<List<PushNotification>>()
        var chunk = mutableListOf<PushNotification>()

        var chunkMessagesCount = 0
        for (message in messages) {
            var partialTo = mutableListOf<String>()
            for (recipient in message.to) {
                if (recipient.isEmpty()) continue
                partialTo.add(recipient)
                chunkMessagesCount++
                if (chunkMessagesCount >= PUSH_NOTIFICATION_CHUNK_LIMIT) {
                    val tmpCopy = message.copy(to = partialTo)
                    chunk.add(tmpCopy)
                    chunks.add(chunk)
                    chunk = mutableListOf()
                    chunkMessagesCount = 0
                    partialTo = mutableListOf()
                }
            }

            if (partialTo.isNotEmpty()) {
                val tmpCopy = message.copy(to = partialTo)
                chunk.add(tmpCopy)
            }

            if (chunkMessagesCount >= PUSH_NOTIFICATION_CHUNK_LIMIT) {
                chunks.add(chunk)
                chunk = mutableListOf()
                chunkMessagesCount = 0
            }
        }

        if (chunkMessagesCount > 0) {
            chunks.add(chunk)
        }

        return chunks
    }
}
