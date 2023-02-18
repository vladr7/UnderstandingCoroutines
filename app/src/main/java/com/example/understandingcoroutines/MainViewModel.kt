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


    fun foo(): Flow<Int> = flow {
        for (i in 1..5) {
            println("Emitting $i")
            emit(i)
        }
    }

    fun main() = runBlocking<Unit> {
        foo().collect { value ->
            if (value == 3) cancel()
            println(value)
        }
    }

}