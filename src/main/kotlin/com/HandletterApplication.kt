package com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HandletterApplication

fun main(args: Array<String>) {
	runApplication<HandletterApplication>(*args)
}
