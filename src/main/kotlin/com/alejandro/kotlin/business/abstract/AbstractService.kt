package com.alejandro.kotlin.business.abstract

abstract interface AbstractService<T> {
    fun findById(id: Long): T
}