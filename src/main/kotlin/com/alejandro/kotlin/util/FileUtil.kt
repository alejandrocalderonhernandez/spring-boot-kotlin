package com.alejandro.kotlin.util

import java.io.File

class FileUtil() {

    companion object {
        fun exist(file: File): Boolean{
            return (file.isFile && file.exists() && file.canRead())
        }
    }
}