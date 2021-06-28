package com.alejandro.kotlin.model

data class SuccessResponse<T>(
        override val code: Int,
        override val message:String,
        val data: T
        ): AbstractResponse(
        code,
        message
        ) {
}