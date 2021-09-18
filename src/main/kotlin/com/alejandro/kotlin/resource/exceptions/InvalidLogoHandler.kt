package com.alejandro.kotlin.resource.exceptions

import com.alejandro.kotlin.util.exception.InvalidLogoException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
class InvalidLogoHandler {

    @ExceptionHandler(InvalidLogoException::class)
    fun handleInvalidLogoException(ex: InvalidLogoException): Map<String, String?> {
        return mapOf("error" to ex.message)
    }
}