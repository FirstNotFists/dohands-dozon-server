package kr.or.dohands.dozon.notification.request

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NonNull

@Data
@AllArgsConstructor
data class PushNotification(
    var to: List<String> = listOf(),
    var data: Map<String, Any>? = null,
    var title: String? = null,
    var subtitle: String? = null,
    var body: String? = null,
    var sound: Sound? = null,
    var ttl: Long? = null,
    var expiration: Long? = null,
    var priority: Priority? = null,
    var badge: Long? = null,
    var channelId: String? = null
) {
    constructor(other: PushNotification) : this(
        to = other.to,
        data = other.data,
        title = other.title,
        subtitle = other.subtitle,
        body = other.body,
        sound = other.sound?.copy(),
        ttl = other.ttl,
        expiration = other.expiration,
        priority = other.priority,
        badge = other.badge,
        channelId = other.channelId
    )

    enum class Priority {
        @JsonProperty("default")
        OK,

        @JsonProperty("high")
        ERROR,

        @JsonProperty("normal")
        NORMAL
    }

    @Data
    data class Sound(
        var critical: Boolean? = null,
        var name: String? = null,
        var volume: Long? = null
    ) {
        fun copy(): Sound {
            return Sound(critical, name, volume)
        }
    }
}

