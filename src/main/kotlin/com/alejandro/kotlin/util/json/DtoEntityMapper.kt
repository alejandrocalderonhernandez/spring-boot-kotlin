package com.alejandro.kotlin.util.json

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class DtoEntityMapper() {

    companion object {
        fun mapTo(model: Any, typeReturn: Class<*>): Any {
            val mapper = ObjectMapper();
            mapper.registerModule(JavaTimeModule())
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            try {
                val json = mapper.writeValueAsString(model);
                return mapper.readValue(json, typeReturn)
            } catch (e: JsonProcessingException) {
                throw RuntimeException ("Error to parse json: " + e.message);
            }
        }
    }
}