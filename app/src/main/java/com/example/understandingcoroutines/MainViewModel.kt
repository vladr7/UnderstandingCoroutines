package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED

class MainViewModel : ViewModel() {

    init {
        viewModelScope.launch {
            val job = async {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(3300L) // delay a bit
            println("main: I'm tired of waiting!")
            job.cancel() // cancels the job
            job.join() // waits for job's completion
            println("main: Now I can quit.")
        }
    }
}