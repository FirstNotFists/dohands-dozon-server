package kr.or.dohands.dozon.notification.response

import com.fasterxml.jackson.annotation.JsonProperty

enum class Status {
    @JsonProperty("ok")
    OK,

    @JsonProperty("error")
    ERROR
}