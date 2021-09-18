package com.alejandro.kotlin.util.exception

class InvalidLogoException(msj: String): RuntimeException(
    "Invalid $msj extension"
){
}