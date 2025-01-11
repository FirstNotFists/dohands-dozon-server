package kr.or.dohands.dozon.notification.response


import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import lombok.Data
import lombok.EqualsAndHashCode

/**
 * Response including receipts for tickets.
 */
@Data
@EqualsAndHashCode(callSuper = false)
data class ReceiptResponse(
    override val data: Map<String, Receipt>
) : BaseResponse<Map<String, ReceiptResponse.Receipt>>() {

    @Data
    @EqualsAndHashCode(callSuper = false)
    data class Receipt(
        var status: Status? = null,
        var message: String? = null,
        var details: Details? = null
    ) : GenericData() {

        @Data
        data class Details(
            var error: Error? = null,
            var sentAt: Int? = null,
            var errorCodeEnum: String? = null,
            var additionalProperties: JsonNode? = null
        ) {
            enum class Error {
                @JsonProperty("DeviceNotRegistered")
                DEVICE_NOT_REGISTERED,

                @JsonProperty("MessageTooBig")
                MESSAGE_TOO_BIG,

                @JsonProperty("MessageRateExceeded")
                MESSAGE_RATE_EXCEEDED,

                @JsonProperty("InvalidCredentials")
                INVALID_CREDENTIALS,

                @JsonProperty("InvalidProviderToken")
                INVALID_PROVIDERTOKEN
            }
        }
    }
}
