package com.alejandro.kotlin

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringKotlinExampleApplication {

	private val logger = LoggerFactory.getLogger(SpringKotlinExampleApplication::class.java)

}

fun main(args: Array<String>) {
	runApplication<SpringKotlinExampleApplication>(*args)
}
