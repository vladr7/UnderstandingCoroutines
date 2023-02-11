package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    init {
        main()
    }

    private fun main() = runBlocking {
        doWorld()
    }

    private suspend fun doWorld() = coroutineScope {
        val job = launch {
            delay(2000L)
            println("World")
            delay(1000L)
        }
        println("Hello!")
        job.join()
        println("Done")
    }
}