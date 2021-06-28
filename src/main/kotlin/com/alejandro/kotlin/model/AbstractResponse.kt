package com.alejandro.kotlin.model

abstract class AbstractResponse(
        open val code: Int,
        open val message:String
        )
{}