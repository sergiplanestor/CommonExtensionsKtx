package com.revolhope.common_coroutines.timeout.error

import kotlinx.coroutines.TimeoutCancellationException

fun Throwable.isTimeoutCancellation(): Boolean =
    this is TimeoutCancellationException