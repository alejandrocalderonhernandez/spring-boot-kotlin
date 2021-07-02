package com.alejandro.kotlin.util.functions

import com.alejandro.kotlin.model.ResponseModel

class AbstractFunctions {

    fun interface ResponseBuilder<E> {
        fun build(model: E): ResponseModel<E>
    }
}