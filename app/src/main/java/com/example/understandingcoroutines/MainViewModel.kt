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
//        zip()
//        combine()
    }

    suspend fun zip() {
        val nums = (1..10).asFlow().onEach { delay(500L) }
        val strs = flowOf("one", "two", "three").onEach { delay(10L) }
        nums.zip(strs) { a, b -> "$a -> $b" }
            .collect { println(it) }
    }

    suspend fun combine() {
        val nums = (1..3).asFlow().onEach { delay(500) }
        val strs = flowOf("one", "two", "three").onEach { delay(3000) }
        val startTime = System.currentTimeMillis()
        nums.combine(strs) { a, b -> "$a -> $b" }
            .collect { value ->
                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
            }
    }
}