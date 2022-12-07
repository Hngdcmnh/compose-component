package com.example.composegoogle

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    val time = measureTimeMillis {
        runBlocking {
            println("Weather forecast")
//            launch {
//                printForecast()
//            }

//            launch{
//                printTemperature()
//            }

            val forecast: Deferred<String> = async {
                printForecast()
            }

            val tem: Deferred<String> = async {
                printTemperature()
            }

            print(forecast.await() + " " + tem.await())
        }
    }
    println("Execution time: ${time / 1000.0} seconds")
}

suspend fun printForecast(): String {
    println("Start forecast")
    delay(1000)
    return ("Sunny")
}

suspend fun printTemperature(): String {
    println("Start temperature")
    delay(1000)
    return ("30\u00b0C")
}

