package com.garasha.coroutinely.calculation

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import kotlin.random.Random

@RestController
class CalcController {

    @GetMapping("/calc")
    fun calcInSequence(): Map<String, String> {
        val aMap = mapOf("time" to LocalDateTime.now().toString())
        return aMap
    }

    suspend fun longComputation(res: MutableList<String>) {
        delay(100L)
        res.add("world!")
    }

    suspend fun getAnInt(): Long {
        val waitTime = Random.nextLong(100, 5000)
        delay(waitTime)
        return waitTime
    }
}