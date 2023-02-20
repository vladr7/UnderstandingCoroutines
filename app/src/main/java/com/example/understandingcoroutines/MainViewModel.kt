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
        return MutableSharedFlow<Int>(replay = 2, onBufferOverflow = BufferOverflow.DROP_OLDEST) // this is EXACTLY a StateFlow (without initial value)
        /**
         * Use SharedFlow when you need a StateFlow with tweaks in its behavior such as extra buffering, replaying more values, or omitting the initial value.
         */
    }

    private fun state() = runBlocking<Unit> {
        println("calling sharedFunc")
        val sharedFlow = sharedFunc()
        viewModelScope.launch {
            launch {
                delay(2100L)
                sharedFlow.emit(1)
                sharedFlow.emit(2)
                sharedFlow.emit(3)
//                delay(2100L)
//                sharedFlow.emit(4)
//                delay(2100L)
//                sharedFlow.emit(5)
//                delay(2100L)
//                sharedFlow.emit(6)
            }
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