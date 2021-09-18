package com.alejandro.kotlin.resource.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
class NoSuchElementHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException): Map<String, String?> {
        return mapOf("error" to ex.message)
    }
}