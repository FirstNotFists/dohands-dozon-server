package kr.or.dohands.dozon.feature

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.*
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale

class DateTest(

) {
    private var format = SimpleDateFormat("yyyy-MM-dd")
    private var date: Date = Date()

    @BeforeEach
    fun setUp() {
        date = format.parse("2024-06-30")
    }

    @Test
    fun `업데이트 날짜를 문자열로 반환한다`( ) {
//        val format = SimpleDateFormat("yyyy-MM-dd")
//        val date: Date = format.parse("2024-06-30")
//        val date = LocalDateTime.now().monthValue.toString() + "-" + LocalDateTime.now().dayOfMonth
//        val month = LocalDateTime.now().month.

        Assertions.assertThat(date).isNotNull()
    }

    @Test
    fun `날짜를 통해서 요일을 확인한다`() {
        val now = LocalDateTime.now()

        val dayOfWeek = now.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)

        println(dayOfWeek)

    }

    @Test
    fun `문자열 형태의 날짜를 표현한다`() {
        val date = LocalDateTime.now()
        println(date.toString().split("-"))
//        println(LocalDateTime.now().toDateTime())



//        val date = LocalDateTime.now().monthOfYear.toString() + "-" + LocalDateTime.now().dayOfMonth
//        println(date)

    }
}