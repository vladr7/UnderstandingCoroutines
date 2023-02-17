package com.example.understandingcoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlin.coroutines.resume

class MainViewModel : ViewModel() {

    init {
        viewModelScope.launch {
            val result = suspendCancellableCoroutine<Boolean> { continuation ->
                redeemPass(continuation)
            }
            delay(1500L)
            println(result)
        }
    }

    private fun redeemPass(
        continuation: CancellableContinuation<Boolean>,
    ) {
        continuation.resume(true)
    }
}