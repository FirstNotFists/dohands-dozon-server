package kr.or.dohands.dozon.notification.handler

import com.fasterxml.jackson.databind.ObjectMapper
import kr.or.dohands.dozon.notification.exception.ErrorResponseException
import kr.or.dohands.dozon.notification.response.BaseResponse
import lombok.AllArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.apache.hc.core5.http.ClassicHttpResponse
import org.apache.hc.core5.http.HttpException
import org.apache.hc.core5.http.io.HttpClientResponseHandler
import org.apache.hc.core5.http.io.entity.EntityUtils
import java.io.IOException
import kotlin.jvm.Throws

@Slf4j
@AllArgsConstructor
class BaseResponseHandler<T>(
    private val responseClass: Class<out BaseResponse<T>>
) : HttpClientResponseHandler<T> {

    companion object {
        private val OBJECT_MAPPER = ObjectMapper()
    }

    @Throws(IOException::class, HttpException::class)
    private fun processResponse(httpResponse: ClassicHttpResponse): BaseResponse<T> {
        val entity = httpResponse.entity ?: throw HttpException("Entity is null")
        val content = EntityUtils.toString(entity)
        println("Received Content: $content")
        return OBJECT_MAPPER.readValue(content, responseClass)
    }

    @Throws(IOException::class, HttpException::class)
    override fun handleResponse(httpResponse: ClassicHttpResponse): T {
        val response = processResponse(httpResponse)
        if (httpResponse.code == 200) {
            return response.data
        }
        throw HttpException(
            "Response with errors",
            ErrorResponseException(
                httpResponse.code,
                httpResponse.reasonPhrase,
                response.errors
            )
        )
    }
}
