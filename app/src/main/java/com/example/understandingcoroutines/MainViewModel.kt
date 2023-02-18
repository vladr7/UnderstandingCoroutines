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
        val flow = simple()
        flow.transform {number ->
            emit("number $number")
            emit("LUL")
            emit("number ${number * 10}")
        }.collect {
            println(it)
        }
    }

    fun simple() = flow<Int> {
        (1..3).forEach {
            emit(it)
        }
    }


}