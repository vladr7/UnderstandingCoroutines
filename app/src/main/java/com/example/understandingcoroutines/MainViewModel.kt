package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    init {
        main()
    }

    private fun main() = runBlocking {
        // also try out launch{}
        val deffered = async {
            loadData()
        }
        val deffered2 = async {
            loadData2()
        }
        println("waiting...")
//        val list = listOf(deffered, deffered2)
//        list.awaitAll()
        val result1 = deffered.await()
        val result2 = deffered2.await()
        println("${result1} + ${result2}")
    }

    suspend fun loadData(): Int {
        println("loading...")
        delay(1000L)
        println("loaded!")
        return 42
    }

    private suspend fun loadData2(): Int {
        println("loading2...")
        delay(1000L)
        println("loaded2!")
        return 100
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