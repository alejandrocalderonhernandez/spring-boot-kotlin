package com.alejandro.kotlin.business.abstract

import org.springframework.data.domain.Page

abstract interface AbstractService<E,ID> {

    fun findById(id: ID): E

    fun findByPage(pageNumber: Int, elementForPage: Int): Page<E>

    fun create(element: E): E

    fun update(id: ID,element: E): E

    fun delete(id: ID): Unit
}