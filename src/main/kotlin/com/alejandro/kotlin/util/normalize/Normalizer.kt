package com.alejandro.kotlin.util.normalize

import com.alejandro.kotlin.util.constants.MyConstants
import java.util.*

class Normalizer {

    companion object {
        fun normalizeImgName(img: String): String {
            return UUID.randomUUID().toString() + "@" + img.replace("\\s+","")
        }
    }
}