package com.revolhope.common_coroutines.suspend

import com.revolhope.common_core.core.errors.run_catching.runSafeAsyncThenFold
import com.revolhope.common_coroutines.context.CoroutineContext
import com.revolhope.common_coroutines.context.get
import com.revolhope.common_coroutines.timeout.CoTimeout
import com.revolhope.common_coroutines.timeout.error.isTimeoutCancellation
import com.revolhope.common_coroutines.timeout.isDurationValid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

fun launchCoroutine(
    scope: CoroutineScope,
    timeout: CoTimeout = CoTimeout.DefaultOnTimeoutThrow,
    block: suspend CoroutineScope.() -> Unit
): Job =
    scope.launch {
        if (timeout.isDurationValid()) {
            runSafeAsyncThenFold(
                action = {
                    withTimeout(timeMillis = timeout.duration.millis) { block() }
                },
                onFailure = {
                    if (it.isTimeoutCancellation()) {
                        timeout.onTimeout.invoke(it)
                    } else {
                        throw it
                    }
                }
            )
        } else {
            block()
        }
    }

fun launchCoroutineDefault(
    timeout: CoTimeout = CoTimeout.DefaultOnTimeoutThrow,
    block: suspend CoroutineScope.() -> Unit
): Job =
    launchCoroutine(CoroutineScope(CoroutineContext.DEFAULT.get()), timeout, block)

fun launchCoroutineIO(
    timeout: CoTimeout = CoTimeout.DefaultOnTimeoutThrow,
    block: suspend CoroutineScope.() -> Unit
): Job =
    launchCoroutine(CoroutineScope(CoroutineContext.IO.get()), timeout, block)

fun launchCoroutineUI(
    timeout: CoTimeout = CoTimeout.DefaultOnTimeoutThrow,
    block: suspend CoroutineScope.() -> Unit
): Job =
    launchCoroutine(CoroutineScope(CoroutineContext.UI.get()), timeout, block)