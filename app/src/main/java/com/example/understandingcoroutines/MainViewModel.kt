package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    init {
        main()
//        fromDocs()
    }

    fun main() = runBlocking {
        val handler = CoroutineExceptionHandler { coroutineContext, exception ->
            println("CoroutineExceptionHandler: $exception")
        }
        viewModelScope.launch(handler) {
            delay(1000L)
            launch {
                delay(500L)
                println("Coroutine 1")
            }
            launch {
                delay(100L)
                throw ArithmeticException()
            }
        }
    }

    private fun fromDocs() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }
        val job = GlobalScope.launch(handler) {
            launch { // the first child
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    withContext(NonCancellable) {
                        println("Children are cancelled, but exception is not handled until all children terminate")
                        delay(100)
                        println("The first child finished its non cancellable block")
                    }
                }
            }
            launch { // the second child
                delay(10)
                println("Second child throws an exception")
                throw ArithmeticException()
            }
        }
        job.join()
    }
}