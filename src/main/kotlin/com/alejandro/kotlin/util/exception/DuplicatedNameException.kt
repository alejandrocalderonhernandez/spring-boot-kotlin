package com.alejandro.kotlin.util.exception

class DuplicatedNameException(msj: String): RuntimeException(
    "The name $msj already exist in databases"
){
}