package com.garasha.coroutinely.calculation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.util.StopWatch
import java.util.concurrent.atomic.AtomicInteger

@RunWith(MockitoJUnitRunner::class)
class CalcControllerTest {

    @InjectMocks
    lateinit var calcController: CalcController

    @Test
    fun aLongMethod() {

        val res = mutableListOf<String>()

        runBlocking {
            val promise = launch(Dispatchers.Default) {
                calcController.longComputation(res)
            }
            res.add("Hello,")
            promise.join()
        }

        println(res)

        assertThat(res).contains("Hello,", "world!")
    }

    @Test
    fun aLotOfCoroutines() {

        runBlocking<Unit> {
            val counter = AtomicInteger(0)
            val numberOfCoroutines = 100000

            val stopWatch = StopWatch()

            stopWatch.start()

            val max = List(numberOfCoroutines) {
                async(Dispatchers.Default) {
                    counter.getAndIncrement()
                    calcController.getAnInt()
                }
            }.map { it.await() }
                    .max()

            stopWatch.stop()
            val totalTime = stopWatch.totalTimeMillis
            println("total time: $totalTime")
            println("max wait time: $max")

            assertThat(counter.get()).isEqualTo(numberOfCoroutines)
        }


    }
}