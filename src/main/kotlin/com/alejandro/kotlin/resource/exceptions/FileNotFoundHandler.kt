package com.alejandro.kotlin.resource.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.io.FileNotFoundException

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
class FileNotFoundHandler {

    @ExceptionHandler(FileNotFoundException::class)
    fun handleInvalidLogoException(ex: FileNotFoundException): Map<String, String?> {
        return mapOf("error" to ex.message)
    }

}