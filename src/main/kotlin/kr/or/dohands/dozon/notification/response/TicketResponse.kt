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
//@Data
//@EqualsAndHashCode(callSuper = false)
class TicketResponse @JsonCreator constructor(
    override val data: List<Ticket>
) : BaseResponse<List<TicketResponse.Ticket>>() {


//    @Data
//    @EqualsAndHashCode(callSuper = false)
    class Ticket (
        @JsonProperty("status")
        var status: String,
        @JsonProperty("id")
        var id: String,
        @JsonProperty("message")
        var message: String,
        @JsonProperty("details")
        var details: Details
    ) : GenericData() {

        enum class Error {
            @JsonProperty("DeviceNotRegistered")
            DEVICE_NOT_REGISTERED,

            @JsonProperty("InvalidCredentials")
            INVALID_CREDENTIALS
        }

        @Data
        class Details(
            @JsonProperty("error")
            var error: Error? = null,
            @JsonProperty("sentAt")
            var sentAt: Int? = null,
            @JsonProperty("additionalProperties")
            var additionalProperties: JsonNode? = null
        )
    }
}


