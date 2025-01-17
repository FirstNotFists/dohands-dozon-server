package kr.or.dohands.dozon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class DozonApplication
fun main(args: Array<String>) {
    runApplication<DozonApplication>(*args)
}
