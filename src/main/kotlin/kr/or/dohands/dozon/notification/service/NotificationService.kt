package kr.or.dohands.dozon.notification.service

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.niamedtech.expo.exposerversdk.ExpoPushNotificationClient
import kr.or.dohands.dozon.notification.request.PushNotification
import kr.or.dohands.dozon.user.service.UserService
import lombok.extern.slf4j.Slf4j
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.springframework.stereotype.Service

@Slf4j
@Service
class NotificationService(
    private val userService: UserService
) : Notification {
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

        try{
            host.sendPushNotifications(listOf(expoPushNotification))
        }catch (exception: MismatchedInputException){

        }

    }

    fun newPost(id : String) {
        val user = userService.findUserById(id)

        // 메시지 내용 및 토큰 입력
        val expoPushNotification = PushNotification().apply{
            to = listOf(user.pushToken)
            title = "신규 게시글 알림"
            body = "신규 게시글이 등록되었어요!! 지금 확인해보세요"
        }

        try{
            host.sendPushNotifications(listOf(expoPushNotification))
        }catch (exception: MismatchedInputException){

        }
    }

    fun jobQuestExp(id: String) {
        val user = userService.findUserById(id)

        val expoPushNotification = PushNotification().apply{
            to = listOf(user.pushToken)
            title = "직무별 퀘스트 경험치 알림"
            body = "직무별 퀘스트 경험치가 들어왔어요!! 지금 확인해보세요"
        }

        try{
            host.sendPushNotifications(listOf(expoPushNotification))
        }catch (exception: MismatchedInputException){

        }
    }

    fun leaderQuestExp(id: String) {
        val user = userService.findUserById(id)

        val expoPushNotification = PushNotification().apply{
            to = listOf(user.pushToken)
            title = "리더부여 퀘스트 경험치 알림"
            body = "리더부여 퀘스트 경험치가 들어왔어요!! 지금 확인해보세요"
        }

        try{
            host.sendPushNotifications(listOf(expoPushNotification))
        }catch (exception: MismatchedInputException){

        }
    }

    fun swordProjectExp(id : String) {
        val user = userService.findUserById(id)

        val expoPushNotification = PushNotification().apply{
            to = listOf(user.pushToken)
            title = "전사 프로젝트 경험치 알림"
            body = "전사 프로젝트 경험치가 들어왔어요!! 지금 확인해보세요"
        }

        try{
            host.sendPushNotifications(listOf(expoPushNotification))
        }catch (exception: MismatchedInputException){

        }
    }

    fun hrEvaluationExp(id: String) {
        val user = userService.findUserById(id)

        val expoPushNotification = PushNotification().apply{
            to = listOf(user.pushToken)
            title = "인사 평가 경험치 알림"
            body = "인사 평가 경험치가 들어왔어요!! 지금 확인해보세요"
        }

        try{
            host.sendPushNotifications(listOf(expoPushNotification))
        }catch (exception: MismatchedInputException){

        }
    }

    fun levelUp(id: String) {
        val user = userService.findUserById(id)

        val expoPushNotification = PushNotification().apply{
            to = listOf(user.pushToken)
            title = "레벨업"
            body = "레벨업을 했어요!! 지금 확인해보세요"
        }

        try{
            host.sendPushNotifications(listOf(expoPushNotification))
        }catch (exception: MismatchedInputException){

        }
    }


}