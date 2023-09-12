package com.verge.theverge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class TheVergeApplication

fun main(args: Array<String>) {
	runApplication<TheVergeApplication>(*args)
}
