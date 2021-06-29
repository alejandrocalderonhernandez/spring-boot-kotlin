package com.alejandro.kotlin.util.json

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.slf4j.LoggerFactory

class JsonToString {

    companion object {
        fun buildJsonString(model: Any): String {
            val mapper = ObjectMapper();
            mapper.registerModule(JavaTimeModule())
            try {
                return mapper.writeValueAsString(model);
            } catch (e: JsonProcessingException) {
                throw RuntimeException ("Error to parse json: " + e.message);
            }
        }
    }
}