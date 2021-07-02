package com.alejandro.kotlin.model

data class ResponseModel<E> (
        override val code: Int,
        override val message: String,
        val data: E,

        ): AbstractResponse(
        code,
        message
        ) {
}