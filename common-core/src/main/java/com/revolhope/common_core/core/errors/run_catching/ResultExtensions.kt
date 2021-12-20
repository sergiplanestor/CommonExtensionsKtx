package com.revolhope.common_core.core.errors.run_catching

suspend inline fun <R, T> Result<T>.foldAsync(
    crossinline onSuccess: suspend (value: T) -> R,
    crossinline onFailure: suspend (exception: Throwable) -> R
): R =
    fold(
        onSuccess = { onSuccess(it) },
        onFailure = { onFailure(it) }
    )

suspend inline fun <R, T : R> Result<T>.getOrElseAsync(
    crossinline onFailure: suspend (exception: Throwable) -> R
): R =
    getOrElse { onFailure(it) }