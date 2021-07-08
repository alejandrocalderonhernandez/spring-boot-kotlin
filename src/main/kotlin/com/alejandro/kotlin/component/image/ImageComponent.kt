package com.alejandro.kotlin.component.image

import com.alejandro.kotlin.component.common.FileComponent
import com.alejandro.kotlin.util.constants.MyConstants
import org.slf4j.LoggerFactory
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

@Component(value = "img")
class ImageComponent: FileComponent {

    private val logger = LoggerFactory.getLogger(FileComponent::class.java)

    override fun save(file: MultipartFile, name: String): Unit {
        val path: Path = Path.of(MyConstants.IMG_BASE_URL).resolve(name).toAbsolutePath()
        try {
            Files.copy(file.inputStream, path)
        } catch (e: IOException) {
            logger.error("Error to save file: ", e)
        }
    }

    override fun get(path: Path): Resource {
        try {
            return UrlResource(path.toUri())
        } catch (e: IOException) {
            logger.error("Error to get file: ", e)
            throw FileNotFoundException("Cant read file")
        }
    }

    override fun delete(fileName: String): Unit {
     val file: File = Path.of(MyConstants.IMG_BASE_URL).resolve(fileName).toFile()
        if (file.exists() && file.canRead()) {
           file.delete()
        } else {
            throw FileNotFoundException("Cant read file")
        }
    }
}