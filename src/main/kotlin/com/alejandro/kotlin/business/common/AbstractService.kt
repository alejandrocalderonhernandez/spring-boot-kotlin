package com.alejandro.kotlin.business.common

import org.springframework.data.domain.Page
import org.springframework.web.multipart.MultipartFile
import java.io.Serializable

abstract interface AbstractService<E: Serializable,ID> {

    fun findById(id: ID): E?

    fun findByPage(pageNumber: Int, elementForPage: Int): Page<E>

    fun create(element: E): E

    fun update(id: ID, element: E): E

    fun delete(id: ID): Unit
}