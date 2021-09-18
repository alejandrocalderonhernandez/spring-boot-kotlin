package com.alejandro.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringKotlinExampleApplication() {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<SpringKotlinExampleApplication>(*args)
		}
	}

}