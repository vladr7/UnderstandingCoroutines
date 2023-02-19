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
        val channel = Channel<Animals>()

        launch {    //coroutine #1
            val producer: SendChannel<Animals> = channel
            producer.send(Animals.RABBIT)
        }
        launch {    //coroutine #2
            val receiver: ReceiveChannel<Animals> = channel
            println(receiver.receive())   //prints RABBIT
        }
    }
}