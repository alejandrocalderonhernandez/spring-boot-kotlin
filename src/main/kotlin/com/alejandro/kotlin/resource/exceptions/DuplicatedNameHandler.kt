package com.alejandro.kotlin.resource.exceptions

import com.alejandro.kotlin.util.exception.DuplicatedNameException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
class DuplicatedNameHandler {

    @ExceptionHandler(DuplicatedNameException::class)
    fun handleDuplicatedNameException(ex: DuplicatedNameException): Map<String, String?> {
        return mapOf("error" to ex.message)
    }
}