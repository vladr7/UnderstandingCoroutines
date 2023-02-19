package com.example.understandingcoroutines

import android.icu.number.Notation.simple
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.*
import java.lang.System.currentTimeMillis
import kotlin.coroutines.resume
import kotlin.system.measureTimeMillis

class MainViewModel : ViewModel() {

    init {
        main()
    }

    enum class Animals { RABBIT, ELEPHANT, SLOTH }

    fun main() = runBlocking<Unit> {
        val channel = Channel<Animals>(1)

        launch {
            repeat(10) {
                delay(100L)
                channel.send(Animals.RABBIT)
            }
        }
        launch {
            repeat(10) {
                delay(200L)
                channel.send(Animals.ELEPHANT)
            }
        }

        delay(2500L)
        repeat(10) {
            println("${channel.receive()}")
        }

        coroutineContext.cancelChildren()
    }
}