package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlin.coroutines.resume

class MainViewModel : ViewModel() {

    init {
        viewModelScope.launch {
            val task1 = async { doSomethingUsefulOne() }
            val task2 = async { doSomethingUsefulTwo() }
            val result1 = task1.await()
            val result2 = task2.await()
            println("${result1 + result2}")
        }
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(2000L) // pretend we are doing something useful here
        println("Done")
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(2000L) // pretend we are doing something useful here, too
        println("Done")
        return 29
    }
}