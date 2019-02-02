package com.garasha.coroutinely

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoroutinelyApplication

fun main(args: Array<String>) {
	runApplication<CoroutinelyApplication>(*args)
}

