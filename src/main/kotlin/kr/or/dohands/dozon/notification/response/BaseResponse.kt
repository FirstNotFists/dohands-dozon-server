package kr.or.dohands.dozon.notification.response

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.databind.JsonNode
import lombok.Data
import lombok.EqualsAndHashCode

/**
 * Base class for responses provided by Expo Push Notification Service.
 */
@Data
abstract class BaseResponse<T> {

    abstract val data: T

    var errors: List<Error> = emptyList()

    open class GenericData {
        /**
         * Store unmapped data in case actual response is varying from specification.
         */
        private val any: MutableMap<String, JsonNode> = mutableMapOf()

        @JsonAnyGetter
        fun getAny(): Map<String, JsonNode> = any

        @JsonAnySetter
        fun addAny(key: String, value: JsonNode) {
            any[key] = value
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    data class Error(
        var code: String? = null,
        var message: String? = null
    ) : GenericData()
}
