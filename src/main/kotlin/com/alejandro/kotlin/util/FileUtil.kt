package com.alejandro.kotlin.util

import com.alejandro.kotlin.util.constants.AppConstants
import java.io.File
import java.nio.file.Path

class FileUtil {

    companion object {
        fun exist(file: File): Boolean{
            return (file.isFile && file.exists() && file.canRead())
        }

        fun toFile(fileName: String): File{
            return Path.of(AppConstants.IMG_BASE_URL).resolve(fileName).toFile()
        }
    }
}