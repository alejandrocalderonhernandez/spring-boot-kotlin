package com.alejandro.kotlin.component.common

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path

interface FileComponent {

    fun save(file: MultipartFile, fileName: String): Unit

    fun get(fileName: String): Resource

    fun delete(fileName: String): Unit
}