package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED

class MainViewModel : ViewModel() {

    init {
//        rendezvousChannel()
//        unlimitedChannel()
//        conflatedChannel()
//        bufferedChannel()
    }

    private fun bufferedChannel() = runBlocking{
        val channel = Channel<String>(2)
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

    private fun conflatedChannel() = runBlocking{
        val channel = Channel<String>(CONFLATED)
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

    private fun unlimitedChannel() = runBlocking{
        val channel = Channel<String>(UNLIMITED)
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

    private fun rendezvousChannel() = runBlocking {
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