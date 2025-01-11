package kr.or.dohands.dozon.notification.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule


class ObjectMapperFactory {

    private val OBJECT_MAPPER = createObjectMapper()

    private fun createObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
//        val objectMapper = ObjectMapper().registerModule(
//            KotlinModule.Builder().build()
//        )
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
        return objectMapper
    }

    private object ObjectMapperFactory {
        init {
            throw UnsupportedOperationException()
        }
    }

    fun getInstance(): ObjectMapper {
        return OBJECT_MAPPER
    }
}