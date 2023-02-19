package com.example.understandingcoroutines

import android.icu.number.Notation.simple
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
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

    fun main() = runBlocking<Unit> {
        val channel = Channel<Int>()
        launch {
            // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
            for (x in 1..5) {
                delay(500L)
                channel.send(x * x)
            }
//            channel.close()
        }
        // here we print five received integers:
        repeat(5) { println(channel.receive()) }
        println("Done!")
    }

}