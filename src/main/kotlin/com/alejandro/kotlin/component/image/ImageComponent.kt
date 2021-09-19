package com.alejandro.kotlin.component.image

import com.alejandro.kotlin.component.common.FileComponent
import com.alejandro.kotlin.util.FileUtil
import com.alejandro.kotlin.util.constants.AppConstants
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

@Component
class ImageComponent(@Value("\${FILE_PATH}") private val filePath: String): FileComponent{

    private val logger = LoggerFactory.getLogger(FileComponent::class.java)

    override fun save(file: MultipartFile, nameImg: String): Unit {
        val path: Path = Path.of(this.filePath).resolve(nameImg).toAbsolutePath()
        try {
            Files.copy(file.inputStream, path)
        } catch (e: IOException) {
            logger.error("Error to save file: ", e)
        }
    }

    override fun get(nameImg: String): Resource {
        val path: Path = Path.of(this.filePath).resolve(nameImg).toAbsolutePath()
        val defaultPath: Path = Path.of(this.filePath).resolve(AppConstants.DEFAULT_LOGO).toAbsolutePath()
        try {
            if (FileUtil.exist(Path.of(this.filePath).resolve(nameImg).toFile())) {
                return UrlResource(path.toUri())
            }
            return UrlResource(defaultPath.toUri())

        } catch (e: IOException) {
            logger.error("Error to get file: ", e)
            throw FileNotFoundException("Cant read file")
        }
    }

    override fun delete(nameImg: String): Unit {
     val file = Path.of(this.filePath).resolve(nameImg).toFile()
        if (FileUtil.exist(file)) {
           file.delete()
        } else {
            throw FileNotFoundException("Cant read file")
        }
    }
}