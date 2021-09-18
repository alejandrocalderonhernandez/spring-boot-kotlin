package com.alejandro.kotlin.resource.exceptions

import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
class ValidationsHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
        fun  handleValidationExceptions(
             ex: MethodArgumentNotValidException): Map<String, String> {
       val errors =  HashMap<String, String>()
        ex.bindingResult.allErrors.forEach { r ->
            println(ex.message)
            val field = r as FieldError
            field.let {
                val fieldName = it.field
                val errorMessage = r.defaultMessage.toString()
                errors[fieldName] = errorMessage
            }

        }
        return errors;
    }
}