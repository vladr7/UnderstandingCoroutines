package com.example.understandingcoroutines

import android.icu.number.Notation.simple
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.*
import kotlin.coroutines.resume
import kotlin.system.measureTimeMillis

class MainViewModel : ViewModel() {

    init {
        main()
    }

    fun main() = runBlocking<Unit> {
        val time = measureTimeMillis {
            simple()
                .buffer()
                .collect { value ->
                    delay(2000)
                    println(value)
                }
        }
        println("Collected in $time ms")
    }

    fun simple(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(1000)
            emit(i)
        }
    }

}