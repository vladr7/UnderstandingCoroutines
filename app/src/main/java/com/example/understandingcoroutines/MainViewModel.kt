package com.example.understandingcoroutines

import android.icu.number.Notation.simple
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.*
import java.lang.Math.log
import kotlin.coroutines.resume
import kotlin.system.measureTimeMillis

class MainViewModel : ViewModel() {

    init {
//        main()
        state()
    }

    fun stateFunc(): MutableStateFlow<Boolean> {
        println("Flow started")
        return MutableStateFlow<Boolean>(true)
    }

    private fun state() = runBlocking<Unit> {
        println("calling stateFunc")
        val stateFlow = stateFunc()
        println("Calling collect...")
        stateFlow.collect { value -> println(value) }
        println("Calling collect again...")
        stateFlow.collect { value -> println(value) }
    }

    fun simple(): Flow<Int> = flow {
        println("Flow started")
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    fun main() = runBlocking<Unit> {
        println("Calling simple function...")
        val flow = simple()
        println("Calling collect...")
        flow.collect { value -> println(value) }
        println("Calling collect again...")
        flow.collect { value -> println(value) }
    }


}