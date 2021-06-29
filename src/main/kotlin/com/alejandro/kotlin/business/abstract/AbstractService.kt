package com.alejandro.kotlin.business.abstract

abstract interface AbstractService<E,ID> {
    fun findById(id: ID): E
}