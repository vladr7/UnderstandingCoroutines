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
        val startTime = currentTimeMillis()
        (1..3).asFlow().onEach { delay(1000) }
            .flatMapConcat { requestFlow(it) }
            .collect { value ->
                println("$value at ${currentTimeMillis() - startTime} ms from start")
            }
    }

    fun requestFlow(i: Int): Flow<String> = flow {
        emit("$i: First")
        delay(3000)
        emit("$i: Second")
    }


}