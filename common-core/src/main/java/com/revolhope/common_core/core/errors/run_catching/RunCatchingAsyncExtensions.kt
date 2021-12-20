package com.revolhope.common_core.core.errors.run_catching

import com.revolhope.common_core.core.log.Logger
import com.revolhope.common_core.core.log.err

suspend fun <T> runSafeAsync(action: suspend () -> T): Result<T> =
    runCatching { action() }.also { it.exceptionOrNull()?.run { Logger.err(error = this) } }

suspend fun <T, R> T.runSafeAsync(action: suspend T.() -> R): Result<R> =
    runCatching { action() }.also { it.exceptionOrNull()?.run { err(error = this) } }

suspend fun <T> runSafeAsyncOrNull(action: suspend () -> T): T? =
    runSafeAsync(action).getOrNull()

suspend fun <T, R> T.runSafeAsyncOrNull(action: suspend T.() -> R): R? =
    runSafeAsync(action).getOrNull()

suspend fun <T> runSafeAsyncOrDefault(default: T, action: suspend () -> T): T =
    runSafeAsyncOrNull(action) ?: default

suspend fun <T, R> T.runSafeAsyncOrDefault(default: R, action: suspend T.() -> R): R =
    runSafeAsyncOrNull(action) ?: default

suspend fun <T> runSafeAsyncOrElse(action: suspend () -> T, onFailure: suspend Throwable.() -> T): T =
    runSafeAsync(action).getOrElseAsync(onFailure)

suspend fun <T, R> T.runSafeAsyncOrElse(action: suspend T.() -> R, onFailure: suspend Throwable.() -> R): R =
    runSafeAsync(action).getOrElseAsync(onFailure)

suspend fun <T> runSafeAsyncThenFold(
    action: suspend () -> T,
    onSuccess: suspend (T) -> Unit = {},
    onFailure: suspend (Throwable) -> Unit = {}
) {
    runSafeAsync(action).foldAsync(onSuccess = onSuccess, onFailure = onFailure)
}

suspend fun <T, R> T.runSafeAsyncThenFold(
    action: suspend T.() -> R,
    onSuccess: suspend (R) -> Unit = {},
    onFailure: suspend (Throwable) -> Unit = {}
) {
    runSafeAsync(action).foldAsync(onSuccess = onSuccess, onFailure = onFailure)
}