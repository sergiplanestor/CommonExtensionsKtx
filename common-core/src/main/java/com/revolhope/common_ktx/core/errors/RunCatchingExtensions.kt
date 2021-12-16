package com.revolhope.common_ktx.core.errors

import com.revolhope.common_ktx.core.log.Logger
import com.revolhope.common_ktx.core.log.err

fun <T> runSafe(action: () -> T): Result<T> =
    runCatching { action() }.also { it.exceptionOrNull()?.run { Logger.err(error = this) } }

fun <T, R> T.runSafe(action: T.() -> R): Result<R> =
    runCatching { action() }.also { it.exceptionOrNull()?.run { err(error = this) } }

fun <T> runSafeOrNull(action: () -> T): T? =
    runSafe(action).getOrNull()

fun <T, R> T.runSafeOrNull(action: T.() -> R): R? =
    runSafe(action).getOrNull()

fun <T> runSafeOrDefault(default: T, action: () -> T): T =
    runSafeOrNull(action) ?: default

fun <T, R> T.runSafeOrDefault(default: R, action: T.() -> R): R =
    runSafeOrNull(action) ?: default

fun <T> runSafeOrElse(action: () -> T, onFailure: Throwable.() -> T): T =
    runSafe(action).getOrElse(onFailure)

fun <T, R> T.runSafeOrElse(action: T.() -> R, onFailure: Throwable.() -> R): R =
    runSafe(action).getOrElse(onFailure)

fun <T> runSafeThenFold(
    action: () -> T,
    onSuccess: (T) -> Unit = {},
    onFailure: (Throwable) -> Unit = {}
) {
    runSafe(action).fold(onSuccess = onSuccess, onFailure = onFailure)
}

fun <T, R> T.runSafeThenFold(
    action: T.() -> R,
    onSuccess: (R) -> Unit = {},
    onFailure: (Throwable) -> Unit = {}
) {
    runSafe(action).fold(onSuccess = onSuccess, onFailure = onFailure)
}