package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED

class MainViewModel : ViewModel() {

    init {
        main()
    }

    private fun main() = runBlocking {
       val channel = Channel<String>()
        launch {
            channel.send("A1")
            channel.send("A2")
            println("A done")
        }
        launch {
            channel.send("B1")
            println("B done")
        }
        launch {
            repeat(5) {
                val x = channel.receive()
                println("Received: $x")
            }
        }
    }


}