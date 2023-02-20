package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {

    init {
        state()
    }

    fun sharedFunc(): MutableSharedFlow<Int> {
        println("Flow started")
        return MutableSharedFlow<Int>(replay = 2, onBufferOverflow = BufferOverflow.DROP_LATEST) // this is EXACTLY a StateFlow (without initial value)
        /**
         * Use SharedFlow when you need a StateFlow with tweaks in its behavior such as extra buffering, replaying more values, or omitting the initial value.
         */
    }

    private fun state() = runBlocking<Unit> {
        println("calling sharedFunc")
        val sharedFlow = sharedFunc()
        delay(1100L)
        sharedFlow.tryEmit(1)
        sharedFlow.tryEmit(2)
        sharedFlow.tryEmit(3)
        sharedFlow.tryEmit(4)
        viewModelScope.launch {
            launch {
                println("Calling collect...")
                sharedFlow.collect { value -> println(value) }
            }
            launch {
                println("Calling collect...")
                sharedFlow.collect { value -> println(value) }
            }
        }

//        println("Calling collect again...")
//        sharedFlow.collect { value -> println(value) }
    }
}