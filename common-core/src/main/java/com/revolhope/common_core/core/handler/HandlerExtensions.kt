package com.revolhope.common_core.core.handler

import android.os.Handler
import android.os.Looper

inline fun doWithDelay(
    onMainLooper: Boolean = true,
    millisDelay: Long? = null,
    crossinline block: () -> Unit
) {
    val looper = if (onMainLooper) {
        Looper.getMainLooper()
    } else {
        Looper.myLooper() ?: Looper.getMainLooper()
    }
    Handler(looper).run {
        millisDelay?.takeIf { it >= 0 }?.let { delay -> postDelayed({ block() }, delay) }
            ?: post { block() }
    }
}

inline fun doOnUi(
    delay: Long? = null,
    crossinline block: () -> Unit
) {
    doWithDelay(millisDelay = delay, block = block)
}