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

    fun simple(): Flow<Int> = flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i) // emit next value
        }
    }

    fun main() = runBlocking<Unit> {
        try {
            simple().collect { value ->
                println(value)
                check(value <= 1) { "Collected $value" }
            }
        } catch (e: Throwable) {
            println("Caught $e")
        }
    }


}