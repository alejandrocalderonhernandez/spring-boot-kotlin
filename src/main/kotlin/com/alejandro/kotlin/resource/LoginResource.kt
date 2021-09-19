package com.alejandro.kotlin.resource

import com.alejandro.kotlin.util.constants.AppConstants
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["v1/login"])
class Login(val encoder: PasswordEncoder) {

    @PostMapping
    fun login(@RequestBody user: AppUser): ResponseEntity<Map<String, String>> {
        if(user.username == AppConstants.USERNAME && encoder.matches(user.password, AppConstants.ENCODED_PASSWORD)) {
            return ResponseEntity.ok(mapOf("authenticated" to "OK"))
        }
        return ResponseEntity.ok(mapOf("authenticated" to "BAD"))
    }
}

data class AppUser(val username: String, val password: String)