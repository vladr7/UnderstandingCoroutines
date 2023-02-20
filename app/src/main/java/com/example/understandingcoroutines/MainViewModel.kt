package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {

    init {
        state()
    }

    fun sharedFunc(): MutableSharedFlow<Boolean> {
        println("Flow started")
        return MutableSharedFlow<Boolean>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST) // this is EXACTLY a StateFlow (without initial value)
        /**
         * Use SharedFlow when you need a StateFlow with tweaks in its behavior such as extra buffering, replaying more values, or omitting the initial value.
         */
    }

    private fun state() = runBlocking<Unit> {
        println("calling sharedFunc")
        val sharedFlow = sharedFunc()
        sharedFlow.emit(true)
        delay(100L)
        println("Calling collect...")
        sharedFlow.collect { value -> println(value) }
        println("Calling collect again...")
        sharedFlow.collect { value -> println(value) }
    }
}