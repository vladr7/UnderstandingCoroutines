package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED

class MainViewModel : ViewModel() {

    init {
//        fooWithTimeoutOrNull()
//        barWithTimeoutOrNull()
    }

    private fun fooWithTimeoutOrNull() {
        viewModelScope.launch {
            val result = withTimeoutOrNull(1300L) {
                repeat(1000) { i -> // repeat 1000
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
                "Done"// will get cancelled before it produces this result
            }
            println("Result is $result")
        }
    }

    private fun barWithTimeoutOrNull() {
        viewModelScope.launch {
            val result = withTimeoutOrNull(1300L) {
                repeat(1) { i -> // repeat 1
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
                "Done"// will NOT get cancelled
            }
            println("Result is $result")
        }
    }
}