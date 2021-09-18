package com.alejandro.kotlin.util

import com.alejandro.kotlin.util.constants.MyConstants
import java.io.File
import java.nio.file.Path

class FileUtil {

    companion object {
        fun exist(file: File): Boolean{
            return  (file.exists() && file.canRead())
        }

        fun toFile(fileName: String): File{
            return Path.of(MyConstants.IMG_BASE_URL).resolve(fileName).toFile()
        }
    }
}