package kr.or.dohands.dozon.notification.response

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import lombok.Data
import lombok.EqualsAndHashCode

/**
 * Response including tickets for push notifications.
 */
class TicketResponse @JsonCreator constructor(
    override val data: List<Any>
) : BaseResponse<List<Any>>() {

    class Ticket (
        var status: String,
        var id: String,
        var message: String? = null,
        var details: Details? = null
    ) : GenericData() {

        enum class Error {
            DEVICE_NOT_REGISTERED,

            INVALID_CREDENTIALS
        }

//        @Data
        class Details(
//            @JsonProperty("error")
            var error: Error? = null,
//            @JsonProperty("sentAt")
            var sentAt: Int? = null,
//            @JsonProperty("additionalProperties")
            var additionalProperties: JsonNode? = null
        )
    }
}
