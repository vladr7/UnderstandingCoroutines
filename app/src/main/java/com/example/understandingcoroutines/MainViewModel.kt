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
        numbers()
            .take(2) // take only the first two
            .collect { value -> println(value) }
    }

    fun numbers(): Flow<Int> = flow {
        try {
            emit(1)
            emit(2)
            println("This line will not execute")
            emit(3)
        } finally {
            println("Finally in numbers")
        }
    }

}