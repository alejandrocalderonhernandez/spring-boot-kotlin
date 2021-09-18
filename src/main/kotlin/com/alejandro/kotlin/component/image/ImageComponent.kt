package com.alejandro.kotlin.component.image

import com.alejandro.kotlin.component.common.FileComponent
import com.alejandro.kotlin.util.FileUtil
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

@Component()
class ImageComponent: FileComponent {

    private val logger = LoggerFactory.getLogger(FileComponent::class.java)

    override fun save(file: MultipartFile, nameImg: String): Unit {
        val path: Path = Path.of(MyConstants.IMG_BASE_URL).resolve(nameImg).toAbsolutePath()
        try {
            Files.copy(file.inputStream, path)
        } catch (e: IOException) {
            logger.error("Error to save file: ", e)
        }
    }

    override fun get(nameImg: String): Resource {
        val path: Path = Path.of(MyConstants.IMG_BASE_URL).resolve(nameImg).toAbsolutePath()
        try {
            return UrlResource(path.toUri())
        } catch (e: IOException) {
            logger.error("Error to get file: ", e)
            throw FileNotFoundException("Cant read file")
        }
    }

    override fun delete(fileName: String): Unit {
     val file = FileUtil.toFile(fileName)
        if (FileUtil.exist(file)) {
           file.delete()
        } else {
            throw FileNotFoundException("Cant read file")
        }
    }
}