package com.alejandro.kotlin.util.json

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.slf4j.LoggerFactory

class JsonMap {

    companion object {
        fun buildFromModel(model: Any): String {
            val mapper = ObjectMapper();
            mapper.registerModule(JavaTimeModule())
            try {
                return mapper.writeValueAsString(model);
            } catch (e: JsonProcessingException) {
                throw RuntimeException ("Error to parse json: " + e.message);
            }
        }

        fun buildFromString(json: String, typeReturn: Class<*>): Any {
            val mapper = ObjectMapper();
            mapper.registerModule(JavaTimeModule())
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            try {
                return mapper.readValue(json, typeReturn)
            } catch (e: JsonProcessingException) {
                throw RuntimeException ("Error to parse json: " + e.message);
            }
        }
    }
}